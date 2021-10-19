package com.example.eshop.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private int errorCode;

    private String errorMessage;

}
