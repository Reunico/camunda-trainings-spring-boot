package com.cbr.bpm.delegate;

import com.cbr.bpm.constant.ProcessVariableConstant;
import com.cbr.bpm.model.Order;
import com.cbr.bpm.service.CrmService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class SaveOrder implements JavaDelegate {

    private final CrmService crmService;

    public SaveOrder(CrmService crmService) {
        this.crmService = crmService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        crmService.saveOrder( (Order) delegateExecution.getVariable(ProcessVariableConstant.ORDER));
    }
}
