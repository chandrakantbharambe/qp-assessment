package com.qp.grocery.service.impl;

import com.qp.grocery.exception.CustomException;
import com.qp.grocery.model.Item;
import com.qp.grocery.repository.GroceryRepository;
import com.qp.grocery.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    GroceryRepository repository;
    @Override
    public Item saveItem(Item item) {
        return repository.save(item);
    }

    @Override
    public void removeItem(Long id) {
         repository.deleteById(id);
    }

    @Override
    public List<Item> getItems() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public int orderItems(List<Item> items) {
        int totalPrice = 0;
        for(Item item : items) {
            Optional<Item> optionalItem = repository.findById(item.getId());
            if(optionalItem.isEmpty()) {
                throw new CustomException(String.format("Item not found by id: %d",item.getId()), HttpStatus.NOT_FOUND);
            }
            Item originalItem = optionalItem.get();
            if(originalItem.getQuantity() < item.getQuantity()) {
                throw new CustomException(String.format("Item is out of stock with id: %d, available quantity of stock: %d", item.getId(), originalItem.getQuantity()), HttpStatus.BAD_REQUEST);
            }
            originalItem.setQuantity(originalItem.getQuantity()-item.getQuantity());
            repository.save(originalItem);
            totalPrice += item.getQuantity() * item.getPrice();
        }
        return totalPrice;
    }

}
