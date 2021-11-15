package com.example.eshop.controller.user;

import com.example.eshop.dto.PageResponseDTO;
import com.example.eshop.dto.mongo.ProductMongoDTO;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.mongo.ProductMongoMapper;
import com.example.eshop.mongo.entity.ProductMongo;
import com.example.eshop.service.mongo.ProductMongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users/products")
@RequiredArgsConstructor
public class UserProductRestController {

    private final ProductMongoService productMongoService;

    private final ProductMongoMapper productMongoMapper;

    @GetMapping
    public PageResponseDTO<ProductMongoDTO> findAllProducts(@RequestParam(required = false, defaultValue = "1") int page,
                                                       @RequestParam(required = false, defaultValue = "5") int rows) {

        PageResponseDTO<ProductMongoDTO> pageResponse = new PageResponseDTO<>();
        Page<ProductMongo> pageProduct = this.productMongoService.getAllProducts(PageRequest.of(Math.decrementExact(page), rows));
        List<ProductMongoDTO> productMongoDTOList = this.productMongoMapper.toProductMongoDTOList(pageProduct.getContent());
        pageResponse.setContent(productMongoDTOList);
        pageResponse.setPage(Math.incrementExact(pageProduct.getNumber()));
        pageResponse.setTotal(pageProduct.getTotalPages());
        pageResponse.setRecords(pageProduct.getTotalElements());

        return pageResponse;
    }

    @GetMapping("/{id}")
    public ProductMongoDTO getProduct(@PathVariable String id) throws ShopException {
        return this.productMongoMapper.toProductMongoDTO(this.productMongoService.getProduct(id));
    }

}
