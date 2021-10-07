package com.example.eshop.controller;

import com.example.eshop.dto.OrderProductDTO;
import com.example.eshop.mapper.OrderProductMapper;
import com.example.eshop.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1")
public class OrderProductRestController {

    private final OrderProductMapper orderProductMapper;

    private final OrderProductService orderProductService;

    @GetMapping("/order-products/{id}")
    public List<OrderProductDTO> findProductInOrders(@PathVariable Long id) {
        return orderProductMapper.toOrderProductDTO(this.orderProductService.getProductsInOrder(id));
    }

    @DeleteMapping("/order-products/{id}")
    public void deleteProductFromOrder(@PathVariable Long id) throws Exception {
        this.orderProductService.deleteOrderProduct(id);
    }

}
