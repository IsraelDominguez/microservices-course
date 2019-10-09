package com.microservices.app.products.models.repository;

import com.microservices.app.products.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
