package com.example.eshop.service;

import com.example.eshop.entity.OrderProduct;

import java.util.Set;

public interface OrderProductService {

    Set<OrderProduct> getProductsInOrder(Long id);

    void deleteOrderProduct(Long id);

}
