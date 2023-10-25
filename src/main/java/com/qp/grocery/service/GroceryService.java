package com.qp.grocery.service;

import com.qp.grocery.model.Item;

import java.util.List;


public interface GroceryService {

    Item saveItem(Item item);

    void removeItem(Long id);

    List<Item> getItems();

    public int orderItems(List<Item> items);


}
