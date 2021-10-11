package com.example.eshop.service;

import com.example.eshop.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(long productId);

}
