package com.microservices.app.items;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // LoadBalance usan Ribbon para el balanceo de carga

    @Bean("clientRest")
    @LoadBalanced
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }

}
