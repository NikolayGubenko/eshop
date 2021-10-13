package com.example.eshop.controller;

import com.example.eshop.dto.ProductDTO;
import com.example.eshop.entity.Product;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    private final ProductMapper productMapper;



    @GetMapping("/{id}")
    public ProductDTO findProduct(@PathVariable long id) {
        return this.productMapper.toProductDTO(productService.getProduct(id));
    }

}
