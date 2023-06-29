package com.cbr.bpm.service;

import com.cbr.bpm.config.ApplicationProperties;
import com.cbr.bpm.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrmServiceImpl implements CrmService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public CrmServiceImpl(RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void saveOrder(Order order) {
        restTemplate.put(applicationProperties.getCrmUrl() + "/" + order.getNumber().toString(), order);
    }
}
