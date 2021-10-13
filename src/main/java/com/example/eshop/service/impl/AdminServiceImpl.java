package com.example.eshop.service.impl;

import com.example.eshop.entity.Order;
import com.example.eshop.repository.OrderRepository;
import com.example.eshop.service.AdminService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final OrderRepository orderRepository;

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void updateOrder(Order updatedOrder, long orderId) throws NotFoundException {
        Order tmpOrder = this.orderRepository.findById(updatedOrder.getId()).orElseThrow(()
                -> new NotFoundException("Order not found"));

        tmpOrder.setOrderStatus(updatedOrder.getOrderStatus());
        tmpOrder.setActive(updatedOrder.isActive());
        this.orderRepository.save(tmpOrder);
    }



}