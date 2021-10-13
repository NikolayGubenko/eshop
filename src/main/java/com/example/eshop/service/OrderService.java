package com.example.eshop.service;

import com.example.eshop.entity.Order;
import javassist.NotFoundException;

import java.util.List;

public interface OrderService {

    List<Order> getAllUserOrders(long userId) throws Exception;

    Order getOrderDetails(long orderId) throws NotFoundException;

    void addNewOrder(Order order, long userId) throws Exception;

    void updateOrder(Order updatedOrder, long orderId, long userId) throws Exception;

    void deleteOrder(long id);
}
