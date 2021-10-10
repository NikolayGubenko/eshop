package com.example.eshop.controller;

import com.example.eshop.dto.NewOrderDTO;
import com.example.eshop.dto.OrderDTO;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
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
    public List<OrderDTO> findAllOrders() {
        return this.orderMapper.toOrderDTOList(this.orderService.getAllOrders());
    }

    @GetMapping
    public List<OrderDTO> findAllUserOrders(@AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {
        return this.orderMapper.toOrderDTOList(this.orderService.getAllUserOrders(customUserDetails.getId()));
    }

    @GetMapping ("/{id}")
    public OrderDTO getOrderDetails(@PathVariable long id) throws Exception {
      return this.orderMapper.toOrderDTO(this.orderService.getOrderDetails(id));
    }

    @PostMapping
    public NewOrderDTO addNewOrder(@RequestBody NewOrderDTO saveOrder) throws Exception {
        this.orderService.addNewOrder(this.orderMapper.toOrder(saveOrder));
        return saveOrder;
    }

    @PutMapping("/{id}")
    public NewOrderDTO updateOrder(@PathVariable long id, @RequestBody NewOrderDTO updatedOrder) throws Exception {
        this.orderService.updateOrder(this.orderMapper.toOrder(updatedOrder), id);
        return updatedOrder;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable long id){
        this.orderService.deleteOrder(id);
    }

}
