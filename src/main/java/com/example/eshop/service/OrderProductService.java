package com.example.eshop.service;

import com.example.eshop.entity.OrderProduct;
import com.example.eshop.entity.Product;

import java.util.List;

public interface OrderProductService {

    List<OrderProduct> getAllOrderProducts();

    OrderProduct addOrderProduct(OrderProduct orderProduct);

}
