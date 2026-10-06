package com.alirasouli.inventory.service;

import com.alirasouli.inventory.exception.ProductNotFoundException;
import com.alirasouli.inventory.model.Order;
import com.alirasouli.inventory.model.Product;
import com.alirasouli.inventory.repository.OrderRepository;
import com.alirasouli.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alirasouli.inventory.exception.InsufficientInventoryException;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(
            OrderRepository orderRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(Order order) {

        Product product = productRepository.findById(order.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(order.getProductId()));

        if (product.getQuantity() < order.getQuantity()) {
            throw new InsufficientInventoryException(order.getProductId());
        }

        product.setQuantity(
                product.getQuantity() - order.getQuantity()
        );

        productRepository.save(product);

        return orderRepository.save(order);
    }
}