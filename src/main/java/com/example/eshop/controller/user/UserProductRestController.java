package com.example.eshop.controller.user;

import com.example.eshop.dto.PageResponseDTO;
import com.example.eshop.dto.ProductDTO;
import com.example.eshop.entity.Product;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users/products")
@RequiredArgsConstructor
public class UserProductRestController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public PageResponseDTO<ProductDTO> findAllProducts(@RequestParam(required = false, defaultValue = "1") int page,
                                                       @RequestParam(required = false, defaultValue = "5") int rows) {

        PageResponseDTO<ProductDTO> pageResponse = new PageResponseDTO<>();
        Page<Product> pageProduct = this.productService.getAllProducts(PageRequest.of(Math.decrementExact(page), rows));
        List<ProductDTO> productDTOList = this.productMapper.toProductDTOList(pageProduct.getContent());
        pageResponse.setContent(productDTOList);
        pageResponse.setPage(Math.incrementExact(pageProduct.getNumber()));
        pageResponse.setTotal(pageProduct.getTotalPages());
        pageResponse.setRecords(pageProduct.getTotalElements());

        return pageResponse;
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable long id) throws ShopException {
        return this.productMapper.toProductDTO(this.productService.getProduct(id));
    }

}
