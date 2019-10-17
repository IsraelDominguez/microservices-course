package com.microservices.app.items.clients;

import com.microservices.app.items.models.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="products-service")
public interface ClientRest {

    @GetMapping("/list")
    List<Product> findAll();


    @GetMapping("/show/{id}")
    Product show(@PathVariable Long id);

}
