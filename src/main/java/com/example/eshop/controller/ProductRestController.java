package com.example.eshop.controller;

import com.example.eshop.dto.ProductDTO;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> findAllProducts() {
        return productMapper.toProductDTOList(productService.getAllProducts());
    }

}
