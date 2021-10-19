package com.example.eshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ValidationResponse {

    private String message;

    private List<String> details;

}
