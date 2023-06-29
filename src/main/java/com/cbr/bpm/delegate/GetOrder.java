package com.cbr.bpm.delegate;

import com.cbr.bpm.constant.ProcessVariableConstant;
import com.cbr.bpm.service.WebsiteService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class GetOrder implements JavaDelegate {

    private final WebsiteService websiteService;

    public GetOrder(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable(ProcessVariableConstant.ORDER, websiteService.getOrder());
    }
}
