package com.example.eshop.service;

import com.example.eshop.entity.Order;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<Order> getAllOrders(Pageable pageable);

    void updateOrder(Order updatedOrder, long orderId) throws NotFoundException;

}
