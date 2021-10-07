package com.example.eshop.service;

import com.example.eshop.entity.OrderProduct;

import java.util.List;

public interface OrderProductService {

    List<OrderProduct> getProductsInOrder(Long id);

    OrderProduct addOrderProduct(OrderProduct orderProduct);

    OrderProduct findOrderProduct(Long id) throws Exception;

    void deleteOrderProduct(Long id);

}
