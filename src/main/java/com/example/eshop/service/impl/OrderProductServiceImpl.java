package com.example.eshop.service.impl;

import com.example.eshop.entity.OrderProduct;
import com.example.eshop.entity.Product;
import com.example.eshop.repository.OrderProductRepository;
import com.example.eshop.service.OrderProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    @Override
    public OrderProduct addOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

}
