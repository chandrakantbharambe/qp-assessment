package com.qp.grocery.controller;


import com.qp.grocery.model.Item;
import com.qp.grocery.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grocery")
public class GroceryController {

    @Autowired
    GroceryService service;

    @PostMapping("/item")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Item saveItem(@RequestBody Item item) {
        return service.saveItem(item);
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return service.getItems();
    }

    @DeleteMapping("/item/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object>  removeItem(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.removeItem(id);
            response.put("deleted", true);
        } catch (Exception ex) {
            response.put("deleted", false);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/item/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Item>  updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return ResponseEntity.ok(service.saveItem(item));
    }

    @PostMapping("/items/order")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> orderItems(@RequestBody List<Item> items) {
         int totalPrice = service.orderItems(items);
         return ResponseEntity.ok(String.format("Your order is placed, total price of items is %d", totalPrice));
    }
}
