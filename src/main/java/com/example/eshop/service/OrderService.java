package com.example.eshop.service;

import com.example.eshop.entity.Order;
import com.example.eshop.exception.ShopException;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    List<Order> getAllUserOrders(long userId) throws Exception;

    Order getOrderDetails(long orderId) throws NotFoundException, ShopException;

    void addNewOrder(Order order, long userId) throws Exception;

    void updateOrder(Order updatedOrder, long orderId, long userId) throws Exception;

    Order adminUpdateOrder(Order updatedOrder, long orderId) throws NotFoundException;

    void deleteOrder(long id);
}
