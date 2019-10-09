package com.microservices.app.items.models.service;

import com.microservices.app.items.models.entity.Item;
import com.microservices.app.items.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemService implements IItemService {

    @Autowired
    RestTemplate clientRest;


    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(clientRest.getForObject("http://products-service/products/list", Product[].class));

        return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());

    }

    @Override
    public Item findById(Long id, Integer amount) {
        Map<String,String> params = new HashMap<String, String>();
        params.put("id", id.toString());

        Product product = clientRest.getForObject("http://products-service/products/show/{id}", Product.class, params);

        return new Item(product, amount);

    }
}
