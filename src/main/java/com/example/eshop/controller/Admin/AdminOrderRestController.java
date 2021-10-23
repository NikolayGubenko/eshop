package com.example.eshop.controller.Admin;

import com.example.eshop.dto.AdminOrderDTO;
import com.example.eshop.dto.PageResponseDTO;
import com.example.eshop.entity.Order;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/orders")
public class AdminOrderRestController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @GetMapping
    public PageResponseDTO<AdminOrderDTO> findAllOrders(@RequestParam(required = false, defaultValue = "1") int page,
                                                        @RequestParam(required = false, defaultValue = "5") int rows) {

        PageResponseDTO<AdminOrderDTO> pageResponse = new PageResponseDTO<>();
        Page<Order> pageOrder = this.orderService.getAllOrders(PageRequest.of(Math.decrementExact(page), rows));
        List<AdminOrderDTO> adminOrderDTOList = this.orderMapper.toAdminOrderDTOList(pageOrder.getContent());
        pageResponse.setContent(adminOrderDTOList);
        pageResponse.setPage(Math.incrementExact(pageOrder.getNumber()));
        pageResponse.setTotal(pageOrder.getTotalPages());
        pageResponse.setRecords(pageOrder.getTotalElements());

        return pageResponse;
    }

    @PutMapping("/{id}")
    public AdminOrderDTO updateOrderStats(@RequestBody AdminOrderDTO updatedOrder,
                                          @PathVariable long id) throws ShopException {

        Order order = this.orderService.adminUpdateOrder(this.orderMapper.toOrder(updatedOrder), id);
        return this.orderMapper.toAdminOrderDTO(order);
    }

}
