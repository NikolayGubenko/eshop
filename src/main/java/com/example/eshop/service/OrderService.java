package com.example.eshop.service;

import com.example.eshop.entity.Order;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    List<Order> getAllUserOrders(long userId) throws Exception;

    Order getOrderDetails(long orderId) throws NotFoundException;

    void addNewOrder(Order order, long userId) throws Exception;

    void updateOrder(Order updatedOrder, long orderId, long userId) throws Exception;

    void deleteOrder(long id);
}
