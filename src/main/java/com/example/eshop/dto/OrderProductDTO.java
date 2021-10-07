package com.example.eshop.dto;

import com.example.eshop.entity.Product;
import lombok.Data;

@Data
public class OrderProductDTO {

    private long id;

    private Product product;

    private long quantity;

}
