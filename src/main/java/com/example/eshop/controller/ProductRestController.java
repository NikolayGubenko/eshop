package com.example.eshop.controller;

import com.example.eshop.dto.ProductDTO;
import com.example.eshop.entity.Product;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public Map<String, Object> findAllOrders(@RequestParam(defaultValue = "0", required = false) Integer page) {
        Pageable paging = PageRequest.of(page, 5);
        Page<Product> pageProducts = productService.getAllProducts(paging);
        List<ProductDTO> productDTOList = productMapper.toProductDTOList(pageProducts.getContent());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("results", productDTOList);

        return response;
    }

    @GetMapping("/{id}")
    public ProductDTO findProduct(@PathVariable long id) throws ShopException {
        return this.productMapper.toProductDTO(productService.getProduct(id));
    }

}
