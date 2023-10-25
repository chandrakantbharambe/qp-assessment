package com.qp.grocery.repository;

import com.qp.grocery.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepository extends JpaRepository<Item, Long> {
}
