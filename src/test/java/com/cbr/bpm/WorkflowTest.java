package com.cbr.bpm;

import com.cbr.bpm.constant.ProcessVariableConstant;
import com.cbr.bpm.model.Order;
import com.cbr.bpm.service.CrmService;
import com.cbr.bpm.service.OrderService;
import com.cbr.bpm.service.WebsiteService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.process_test_coverage.junit4.platform7.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowTest extends AbstractProcessEngineRuleTest {

  @Autowired
  public RuntimeService runtimeService;

  @MockBean
  WebsiteService websiteService;

  @MockBean
  OrderService orderService;

  @MockBean
  CrmService crmService;

  @Rule
  @ClassRule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  @Before
  public void setup() {
    Mockito.when(websiteService.getOrder()).thenReturn(getOrder());
  }

  private Order getOrder() {
    Order order = new Order();
    order.setAmount(new BigDecimal(300));
    order.setOrderDate(new Date());
    order.setTitle("Test Position");
    order.setFullName("Test Customer");
    return order;
  }
  @Test
  @Deployment(resources = {"partymaker.bpmn", "proposal.dmn"})
  public void shouldExecuteHappyPath() {
    // given
    String processDefinitionKey = "partymaker";

    // when
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

    // then
    assertThat(processInstance).isStarted();
    assertThat(processInstance).isWaitingAt(("Activity_getOrder"));
    execute(job()); // execute job Activity_getOrder due to save point
    assertThat(task()).hasDefinitionKey("Activity_approveTask").isNotAssigned();

    complete(task(), withVariables(
            ProcessVariableConstant.ORDER_APPROVED, true,
            ProcessVariableConstant.DESCRIPTION, "Test description",
            ProcessVariableConstant.CONTRACTOR, "Василек",
            ProcessVariableConstant.ORDER_DATE, Date.from(LocalDate.now().plusDays(1L).
                    atStartOfDay(ZoneId.systemDefault()).toInstant())
            ));

    assertThat(processInstance).isWaitingAt("Activity_persistOrder");
    execute(job()); // execute job persistOrder due to Save Point
    assertThat(processInstance).isWaitingAt("Event_eventDate");
    execute(job()); // execute timer
    assertThat(processInstance).isEnded();
  }

}
