package com.example.eshop.service.impl;

import com.example.eshop.entity.Order;
import com.example.eshop.entity.User;
import com.example.eshop.repository.OrderRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllUserOrders(String userName) throws Exception {
        User user = userRepository.findUserByEmail(userName).orElseThrow(() -> new Exception("User not found"));
        return this.orderRepository.findAllByUserId(user.getId());
    }

    @Override
    public void addNewOrder(Order order, String userName) {
        if (!order.getOrderProducts().isEmpty()) {
            this.orderRepository.save(order);
        }
    }

    @Override
    public void updateOrder(Order updatedOrder, String userName) throws Exception {
        Order tmpOrder = this.orderRepository.findById(updatedOrder.getId()).orElseThrow(() -> new Exception("Order not found"));

        if (!updatedOrder.getOrderProducts().isEmpty()) {
            tmpOrder.setOrderProducts(updatedOrder.getOrderProducts());
        }

        tmpOrder.setDescription(updatedOrder.getDescription());
        tmpOrder.setPostalOffice(updatedOrder.getPostalOffice());

        this.orderRepository.save(updatedOrder);
    }
}
