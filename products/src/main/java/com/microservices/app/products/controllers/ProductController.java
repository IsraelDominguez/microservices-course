package com.microservices.app.products.controllers;

import com.microservices.app.products.models.entity.Product;
import com.microservices.app.products.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private Environment env;


    //Otra manera de coger valores del properties
    @Value("${server.port}")
    private Integer port;

    @GetMapping("/products/list")
    public List<Product> list() {
        return productService.findAll().stream().map(p -> {
            p.setPort(port);
            return p;
        }).collect(Collectors.toList());
    }

    @GetMapping("/products/show/{id}")
    public Product show(@PathVariable Long id) {
        Product product = productService.findById(id).orElse(null);

        product.setPort(port);

        return product;
    }

}
