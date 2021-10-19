package com.example.eshop.service.impl;

import com.example.eshop.entity.Order;
import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.repository.OrderRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public List<Order> getAllUserOrders(long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order getOrderDetails(long orderId) throws ShopException {
        return this.orderRepository.findById(orderId).orElseThrow(() -> new ShopException(Error.ORDER_NOT_FOUND));
    }

    @Override
    public void addNewOrder(Order order, long userId) throws NotFoundException {

        order.setOrderDate(LocalDateTime.now());
        order.getOrderProducts().forEach((OrderProduct) -> OrderProduct.setOrder(order));
        order.setUser(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));

        this.orderRepository.save(order);

    }

    @Override
    public void updateOrder(Order updatedOrder, long orderId, long userId) throws Exception {
        Order tmpOrder = this.orderRepository.findById(updatedOrder.getId()).orElseThrow(()
                -> new NotFoundException("Order not found"));

        tmpOrder.getOrderProducts().clear();
        tmpOrder.getOrderProducts().addAll(updatedOrder.getOrderProducts());
        tmpOrder.getOrderProducts().forEach((OrderProduct) -> OrderProduct.setOrder(updatedOrder));
        tmpOrder.setUser(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
        this.orderRepository.save(tmpOrder);
    }

    @Override
    public Order adminUpdateOrder(Order updatedOrder, long orderId) throws NotFoundException {
        Order tmpOrder = this.orderRepository.findById(updatedOrder.getId())
                .orElseThrow(() -> new NotFoundException("Order not found"));

        tmpOrder.setOrderStatus(updatedOrder.getOrderStatus());
        tmpOrder.setActive(updatedOrder.isActive());
        return this.orderRepository.save(tmpOrder);
    }

    @Override
    public void deleteOrder(long id) {
        this.orderRepository.deleteById(id);
    }
}
