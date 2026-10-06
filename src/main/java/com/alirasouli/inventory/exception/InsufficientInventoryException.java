package com.alirasouli.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientInventoryException extends RuntimeException {

    public InsufficientInventoryException(Long productId) {
        super("Not enough inventory for product with id: " + productId);
    }
}