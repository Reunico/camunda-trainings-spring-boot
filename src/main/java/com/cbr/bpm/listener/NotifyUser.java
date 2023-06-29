package com.cbr.bpm.listener;

import com.cbr.bpm.service.NotifyService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class NotifyUser implements TaskListener {

    private final NotifyService notifyService;

    public NotifyUser(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        notifyService.notify(delegateTask.getId(), delegateTask.getName(), "");
    }
}
