package com.example.eshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {ShopException.class})
    public ResponseEntity<ErrorResponse> handleShopException(ShopException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ex.getError().getErrorCode());
        errorResponse.setErrorMessage(ex.getError().getErrorMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> details = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.put(error.getField(), error.getDefaultMessage());
        }
        ValidationResponse validationError = new ValidationResponse("Validation failed", details);

        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

}
