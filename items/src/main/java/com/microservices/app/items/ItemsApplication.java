package com.microservices.app.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name="products-service")
@EnableFeignClients
@SpringBootApplication
public class ItemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemsApplication.class, args);
    }

}
