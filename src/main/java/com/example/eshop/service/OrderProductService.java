package com.example.eshop.service;

import com.example.eshop.mysql.entity.OrderProduct;

import java.util.Set;

public interface OrderProductService {

    Set<OrderProduct> getProductsInOrder(Long id);

    void deleteOrderProduct(Long id);

}
