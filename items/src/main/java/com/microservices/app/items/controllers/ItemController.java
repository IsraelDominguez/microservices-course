package com.microservices.app.items.controllers;

import com.microservices.app.items.models.entity.Item;
import com.microservices.app.items.models.entity.Product;
import com.microservices.app.items.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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


    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "ifErrorExist")
    @GetMapping("/show/{id}/amount/{amount}")
    public Item show(@PathVariable Long id, @PathVariable Integer amount) {
        return itemService.findById(id, amount);
    }


    /**
     * Manage Errors with Hystris
     *
     * @param id
     * @param amount
     * @return
     */
    public Item ifErrorExist(@PathVariable Long id, @PathVariable Integer amount) {

        Item item = new Item();
        Product producto = new Product();

        item.setAmount(amount);
        producto.setId(id);
        producto.setName("Default Name");
        producto.setPrize(0.00);
        item.setProduct(producto);
        return item;

    }

}
