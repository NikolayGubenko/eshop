package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductDTO {

    private long id;

    @NotBlank(message="Enter product name")
    private String name;

    @NotNull (message="Enter product price")
    @DecimalMin(value = "5.0", message = "Product price must be between 5 and 100000")
    @DecimalMax(value = "100000.0", message = "Product price must be between 5 and 100000")
    private BigDecimal price;

    @NotBlank(message="Enter product type")
    private String productType;

}
