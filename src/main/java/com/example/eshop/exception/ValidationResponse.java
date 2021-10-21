package com.example.eshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class ValidationResponse {

    private String message;

    private Map<String, String> details;

}
