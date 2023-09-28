package com.cbr.bpm.dmn;


import com.cbr.bpm.constant.ProcessVariableConstant;
import com.cbr.bpm.model.Order;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.engine.DecisionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProposalTest {

    @Autowired
    DecisionService decisionService;

    @Test
    public void testProposalOk() {
        String then = "Морской круиз";
        Map<String,Object> variables = new HashMap<>();
        Order order = new Order();
        order.setAmount(new BigDecimal(900));
        variables.put(ProcessVariableConstant.ORDER, order);
        DmnDecisionResult result = decisionService.evaluateDecisionByKey("proposal").variables(variables).evaluate();
        Assert.assertEquals(result.getSingleEntry(),then);
    }

    @Test
    public void testProposalFail() {
        String then = "Морской круиз";
        Map<String,Object> variables = new HashMap<>();
        Order order = new Order();
        order.setAmount(new BigDecimal(100));
        variables.put(ProcessVariableConstant.ORDER, order);
        DmnDecisionResult result = decisionService.evaluateDecisionByKey("proposal").variables(variables).evaluate();
        Assert.assertNotEquals(result.getSingleEntry(),then);
    }
}
