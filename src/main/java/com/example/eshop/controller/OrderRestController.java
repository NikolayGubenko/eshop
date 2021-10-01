package com.example.eshop.controller;

import com.example.eshop.entity.Order;
import com.example.eshop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> findAllOrders() {
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

}
