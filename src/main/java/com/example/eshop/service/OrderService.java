package com.example.eshop.service;

import com.example.eshop.entity.Order;
import com.example.eshop.exception.ShopException;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    Page<Order> getAllUserOrders(Pageable pageable, long userId) throws Exception;

    Order getOrderDetails(long orderId) throws NotFoundException, ShopException;

    Order addNewOrder(Order order, long userId) throws Exception;

    Order updateOrder(Order updatedOrder, long orderId, long userId) throws Exception;

    Order adminUpdateOrder(Order updatedOrder, long orderId) throws ShopException;

    void deleteOrder(long id) throws ShopException;
}
