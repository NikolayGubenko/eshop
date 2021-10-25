package com.example.eshop.controller.User;

import com.example.eshop.dto.NewOrderDTO;
import com.example.eshop.dto.OrderDTO;
import com.example.eshop.dto.PageResponseDTO;
import com.example.eshop.entity.Order;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/orders")
public class UserOrderRestController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @GetMapping
    public PageResponseDTO<OrderDTO> findAllUserOrders(@RequestParam(required = false, defaultValue = "1") int page,
                                                       @RequestParam(required = false, defaultValue = "5") int rows,
                                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {

        PageResponseDTO<OrderDTO> pageResponse = new PageResponseDTO<>();
        Page<Order> pageOrder = this.orderService.getAllUserOrders(PageRequest.of(Math.decrementExact(page), rows), customUserDetails.getId());
        List<OrderDTO> orderDTOList = this.orderMapper.toOrderDTOList(pageOrder.getContent());
        pageResponse.setContent(orderDTOList);
        pageResponse.setPage(Math.incrementExact(pageOrder.getNumber()));
        pageResponse.setTotal(pageOrder.getTotalPages());
        pageResponse.setRecords(pageOrder.getTotalElements());

        return pageResponse;
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
