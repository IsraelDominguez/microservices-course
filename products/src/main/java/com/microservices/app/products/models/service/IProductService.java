package com.microservices.app.products.models.service;

import com.microservices.app.products.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

}
