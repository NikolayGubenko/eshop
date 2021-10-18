package com.example.eshop.controller.Admin;

import com.example.eshop.dto.AdminOrderDTO;
import com.example.eshop.entity.Order;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/orders")
public class AdminOrderRestController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @GetMapping
    public Map<String, Object> findAllOrders(@RequestParam(required = false) Integer page,
                                             @RequestParam(defaultValue = "5") Integer rows) {
        Pageable paging = PageRequest.of(page-1, rows);
        Page<Order> pageOrders = orderService.getAllOrders(paging);
        List<AdminOrderDTO> orderDTOList = orderMapper.toAdminOrderDTOList(pageOrders.getContent());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("content", orderDTOList);
        response.put("page", pageOrders.getNumber()+1);
        response.put("total", pageOrders.getTotalPages());
        response.put("records", pageOrders.getTotalElements());
        return response;
    }

    @PutMapping("/{id}")
    public AdminOrderDTO updateOrderStats(@RequestBody AdminOrderDTO updatedOrder,
                                          @PathVariable long id) throws NotFoundException {
        Order order = this.orderService.adminUpdateOrder(this.orderMapper.toOrder(updatedOrder), id);
        return this.orderMapper.toAdminOrderDTO(order);
    }

}
