package com.example.eshop.exception;

import lombok.Getter;

@Getter
public class ShopException extends Exception {

    private final Error error;

    public ShopException(Error error) {
        super(error.getErrorMessage());
        this.error = error;
    }
}
