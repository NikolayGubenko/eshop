package com.example.eshop.service;

import com.example.eshop.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getAllUserOrders(String userName) throws Exception;

    void addNewOrder(Order order, String userName);

    void updateOrder(Order updatedOrder, String userName) throws Exception;

}
