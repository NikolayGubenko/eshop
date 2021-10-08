package com.example.eshop.service;

import com.example.eshop.entity.OrderProduct;

import java.util.List;
import java.util.Set;

public interface OrderProductService {

    Set<OrderProduct> getProductsInOrder(Long id);

    OrderProduct addOrderProduct(OrderProduct orderProduct);

    OrderProduct findOrderProduct(Long id) throws Exception;

    void deleteOrderProduct(Long id);

}
