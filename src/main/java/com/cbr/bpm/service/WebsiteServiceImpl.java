package com.cbr.bpm.service;

import com.cbr.bpm.config.ApplicationProperties;
import com.cbr.bpm.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebsiteServiceImpl implements WebsiteService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public WebsiteServiceImpl(RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public Order getOrder() {
        return restTemplate.getForObject(applicationProperties.getWebsiteUrl(), Order.class);
    }
}
