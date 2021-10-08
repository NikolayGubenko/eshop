package com.example.eshop.service;

import com.example.eshop.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getAllUserOrders(long userId) throws Exception;

    void addNewOrder(Order order) throws Exception;

    void updateOrder(Order updatedOrder, long orderId) throws Exception;

    void deleteOrder(long id);
}
