package com.example.eshop.service.impl;

import com.example.eshop.entity.Order;
import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.repository.OrderRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public Page<Order> getAllUserOrders(Pageable pageable, long userId) {
        return this.orderRepository.findAllByUserIdAndActiveTrue(pageable, userId);
    }

    @Override
    public Order getOrderDetails(long orderId) throws ShopException {
        return this.orderRepository.findById(orderId).orElseThrow(() -> new ShopException(Error.ORDER_NOT_FOUND));
    }

    @Override
    public Order addNewOrder(Order order, long userId) throws ShopException {

        order.getOrderProducts().forEach((OrderProduct) -> OrderProduct.setOrder(order));
        order.setOrderDate(LocalDateTime.now());
        order.setUser(userRepository.findById(userId).orElseThrow(() -> new ShopException(Error.USER_NOT_FOUND)));
        order.setActive(true);

        return this.orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order updatedOrder, long orderId, long userId) throws ShopException {
        Order tmpOrder = this.orderRepository.findById(updatedOrder.getId())
                .orElseThrow(() -> new ShopException(Error.ORDER_NOT_FOUND));

        tmpOrder.getOrderProducts().clear();
        tmpOrder.getOrderProducts().addAll(updatedOrder.getOrderProducts());
        tmpOrder.getOrderProducts().forEach((OrderProduct) -> OrderProduct.setOrder(updatedOrder));
        tmpOrder.setUser(userRepository.findById(userId).orElseThrow(() -> new ShopException(Error.USER_NOT_FOUND)));

        return this.orderRepository.save(tmpOrder);
    }

    @Override
    public Order adminUpdateOrder(Order updatedOrder, long orderId) throws ShopException {
        Order tmpOrder = this.orderRepository.findById(updatedOrder.getId())
                .orElseThrow(() -> new ShopException(Error.ORDER_NOT_FOUND));

        tmpOrder.setOrderStatus(updatedOrder.getOrderStatus());
        tmpOrder.setActive(updatedOrder.isActive());
        return this.orderRepository.save(tmpOrder);
    }

    @Override
    public void deleteOrder(long id) throws ShopException {
        Order deletedOrder = this.orderRepository.findById(id).orElseThrow(() -> new ShopException(Error.ORDER_NOT_FOUND));
        deletedOrder.setActive(false);
        this.orderRepository.save(deletedOrder);
    }
}
