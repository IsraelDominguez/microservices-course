package com.microservices.app.items.models.service;

import com.microservices.app.items.models.entity.Item;

import java.util.List;

public interface IItemService {

    List<Item> findAll();

    Item findById(Long id, Integer amount);
}
