package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductDTO {

    private long id;

    @NotBlank
    private String name;


    private BigDecimal price;

    private String productType;

}
