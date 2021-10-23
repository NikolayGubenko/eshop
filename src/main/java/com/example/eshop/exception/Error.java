package com.example.eshop.exception;

import lombok.Getter;

@Getter
public enum Error {
    ORDER_NOT_FOUND(1, "Order not found"),
    PRODUCT_NOT_FOUND(2, "Product not found"),
    USER_NOT_FOUND(3, "User not found"),
    POSTAL_OFFICE_NOT_FOUND(4, "Postal office not found"),
    USER_ALREADY_EXISTS(5, "User already exists");

    private final int errorCode;
    private final String errorMessage;

    Error(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
