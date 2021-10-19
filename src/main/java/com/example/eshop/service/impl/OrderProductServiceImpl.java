package com.example.eshop.service.impl;

import com.example.eshop.entity.OrderProduct;
import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.repository.OrderProductRepository;
import com.example.eshop.service.OrderProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public Set<OrderProduct> getProductsInOrder(Long id) {
        return orderProductRepository.findOrderProductsByOrderId(id);
    }

    @Override
    public OrderProduct addOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public OrderProduct findOrderProduct(Long id) throws Exception {
        Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findById(id);
        return optionalOrderProduct.orElseThrow(() -> new ShopException(Error.ORDER_NOT_FOUND));
    }

    @Override
    public void deleteOrderProduct(Long id) {
        this.orderProductRepository.deleteById(id);
    }

}
