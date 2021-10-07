package com.example.eshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private long id;

    private String name;

    private BigDecimal price;

    private String productType;

}
