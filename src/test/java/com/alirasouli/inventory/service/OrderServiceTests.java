package com.alirasouli.inventory.service;

import com.alirasouli.inventory.repository.OrderRepository;
import com.alirasouli.inventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.alirasouli.inventory.model.Order;
import com.alirasouli.inventory.model.Product;
import com.alirasouli.inventory.exception.InsufficientInventoryException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.alirasouli.inventory.exception.ProductNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, productRepository);
    }

    @Test
    void createOrderShouldReduceProductQuantity() {

        Product product = new Product();
        product.setName("Mechanical Keyboard");
        product.setDescription("RGB mechanical keyboard");
        product.setPrice(89.99);
        product.setQuantity(5);

        Order order = new Order(1L, 2);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(orderRepository.save(order))
                .thenReturn(order);

        orderService.createOrder(order);

        assertEquals(3, product.getQuantity());

        verify(productRepository).save(product);
        verify(orderRepository).save(order);
    }

    @Test
    void createOrderShouldRejectInsufficientInventory() {

        Product product = new Product();
        product.setName("Mechanical Keyboard");
        product.setDescription("RGB mechanical keyboard");
        product.setPrice(89.99);
        product.setQuantity(5);

        Order order = new Order(1L, 10);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        assertThrows(
                InsufficientInventoryException.class,
                () -> orderService.createOrder(order)
        );

        assertEquals(5, product.getQuantity());

        verify(productRepository, never()).save(product);
        verify(orderRepository, never()).save(order);
    }

    @Test
    void createOrderShouldRejectMissingProduct() {

        Order order = new Order(999L, 2);

        when(productRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> orderService.createOrder(order)
        );

        verify(productRepository, never()).save(any());
        verify(orderRepository, never()).save(any());
    }  

}