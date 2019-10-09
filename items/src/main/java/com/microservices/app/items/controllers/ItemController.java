package com.microservices.app.items.controllers;

import com.microservices.app.items.models.entity.Item;
import com.microservices.app.items.models.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {


    // Cargamos la interface y con Primary indicamos que implementación usamos o con @Qualifier("name of implementation")
    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;


    @GetMapping("/items/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @GetMapping("/items/show/{id}/amount/{amount}")
    public Item show(@PathVariable Long id, @PathVariable Integer amount) {
        return itemService.findById(id, amount);
    }

}
