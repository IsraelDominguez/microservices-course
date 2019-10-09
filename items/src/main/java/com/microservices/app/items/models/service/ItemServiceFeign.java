package com.microservices.app.items.models.service;

import com.microservices.app.items.clients.ClientRest;
import com.microservices.app.items.models.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

    @Autowired
    ClientRest clientRestFeign;

    @Override
    public List<Item> findAll() {

        return clientRestFeign.findAll().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {

        return new Item(clientRestFeign.show(id), amount);
    }

}
