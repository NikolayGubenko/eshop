package com.example.eshop.controller;

import com.example.eshop.dto.NewOrderDTO;
import com.example.eshop.dto.OrderDTO;
import com.example.eshop.entity.Order;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @GetMapping("/all")
    public List<OrderDTO> findAllOrders() {
        return this.orderMapper.toOrderDTOList(this.orderService.getAllOrders());
    }

    @GetMapping
    public List<OrderDTO> findAllUserOrders(@AuthenticationPrincipal UserDetails userDetails) throws Exception {
        return this.orderMapper.toOrderDTOList(this.orderService.getAllUserOrders(userDetails.getUsername()));
    }

    @PutMapping
    public NewOrderDTO updateOrder(@AuthenticationPrincipal UserDetails userDetails, NewOrderDTO updatedOrder) throws Exception {
        this.orderService.updateOrder(this.orderMapper.toOrder(updatedOrder),userDetails.getUsername());
        return updatedOrder;
    }

}
