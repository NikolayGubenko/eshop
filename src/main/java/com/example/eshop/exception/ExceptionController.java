package com.example.eshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

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

        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ValidationResponse validationError = new ValidationResponse("Validation failed", details);

        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

}
