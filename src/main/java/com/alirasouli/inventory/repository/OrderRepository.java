package com.alirasouli.inventory.repository;

import com.alirasouli.inventory.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}