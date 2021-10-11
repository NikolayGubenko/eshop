package com.example.eshop.controller;

import com.example.eshop.dto.NewOrderDTO;
import com.example.eshop.dto.OrderDTO;
import com.example.eshop.entity.Order;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @GetMapping("/all")
    public Page<OrderDTO> findAllOrders(Pageable pageable) {

        Page<Order> pageOrders = orderService.getAllOrders(pageable);
        List<OrderDTO> orderDTOList = orderMapper.toOrderDTOList(pageOrders.getContent());
        return new PageImpl<>(orderDTOList, pageable, pageOrders.getTotalElements());
    }

    @GetMapping
    public List<OrderDTO> findAllUserOrders(@AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {
        return this.orderMapper.toOrderDTOList(this.orderService.getAllUserOrders(customUserDetails.getId()));
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderDetails(@PathVariable long id) throws Exception {
        return this.orderMapper.toOrderDTO(this.orderService.getOrderDetails(id));
    }

    @PostMapping
    public NewOrderDTO addNewOrder(@RequestBody NewOrderDTO saveOrder,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {
        this.orderService.addNewOrder(this.orderMapper.toOrder(saveOrder), customUserDetails.getId());
        return saveOrder;
    }

    @PutMapping("/{id}")
    public NewOrderDTO updateOrder(@PathVariable long id,
                                   @RequestBody NewOrderDTO updatedOrder,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {
        this.orderService.updateOrder(this.orderMapper.toOrder(updatedOrder), id, customUserDetails.getId());
        return updatedOrder;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable long id) {
        this.orderService.deleteOrder(id);
    }

}
