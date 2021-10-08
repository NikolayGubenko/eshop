package com.example.eshop.service.impl;

import com.example.eshop.entity.Order;
import com.example.eshop.repository.OrderRepository;
import com.example.eshop.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllUserOrders(long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewOrder(Order order) {

        order.setOrderDate(LocalDateTime.now());
        order.getOrderProducts().forEach((OrderProduct) -> OrderProduct.setOrder(order));

        this.orderRepository.save(order);

    }

    @Override
    public void updateOrder(Order updatedOrder, long orderId) throws Exception {
        Order tmpOrder = this.orderRepository.findById(updatedOrder.getId()).orElseThrow(() -> new NotFoundException("Order not found"));


        tmpOrder.getOrderProducts().clear();
        tmpOrder.setOrderProducts(updatedOrder.getOrderProducts());
        tmpOrder.getOrderProducts().forEach((OrderProduct) -> OrderProduct.setOrder(updatedOrder));
        this.orderRepository.save(tmpOrder);
    }

    @Override
    public void deleteOrder(long id) {
        this.orderRepository.deleteById(id);
    }
}
