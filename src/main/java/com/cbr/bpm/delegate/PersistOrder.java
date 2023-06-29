package com.cbr.bpm.delegate;

import com.cbr.bpm.constant.ProcessVariableConstant;
import com.cbr.bpm.model.Order;
import com.cbr.bpm.service.OrderService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PersistOrder implements JavaDelegate {

    private final OrderService orderService;

    public PersistOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Order order = orderService.persistOrder(
                (Order) delegateExecution.getVariable(ProcessVariableConstant.ORDER),
                (String) delegateExecution.getVariable(ProcessVariableConstant.DESCRIPTION),
                (String) delegateExecution.getVariable(ProcessVariableConstant.CONTRACTOR),
                (Date) delegateExecution.getVariable(ProcessVariableConstant.ORDER_DATE),
                (String) delegateExecution.getVariable(ProcessVariableConstant.CUSTOMER_NAME),
                (String) delegateExecution.getVariable(ProcessVariableConstant.TITLE),
                (Long) delegateExecution.getVariable(ProcessVariableConstant.AMOUNT)
        );

        delegateExecution.setVariable(ProcessVariableConstant.ORDER, order);
    }
}
