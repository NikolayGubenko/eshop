package com.example.eshop.controller.admin;

import com.example.eshop.dto.mongo.ProductMongoDTO;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.mongo.ProductMongoMapper;
import com.example.eshop.mongo.entity.ProductMongo;
import com.example.eshop.service.mongo.ProductMongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/products")
public class AdminProductRestController {

    private final ProductMongoService productMongoService;

    private final ProductMongoMapper productMongoMapper;

    @PostMapping
    public ProductMongoDTO addProduct(@Valid @RequestBody ProductMongoDTO productMongoDTO) {
        ProductMongo product = this.productMongoService.saveProduct(this.productMongoMapper.toProductMongo(productMongoDTO));
        return this.productMongoMapper.toProductMongoDTO(product);
    }

    @PostMapping("/csv-upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFile) {
        if (uploadedFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        this.productMongoService.upload(uploadedFile);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ProductMongoDTO updateProduct(@Valid @RequestBody ProductMongoDTO updatedProduct,
                                         @PathVariable String id) throws ShopException {

        ProductMongo product = this.productMongoService.updateProduct(this.productMongoMapper.toProductMongo(updatedProduct), id);
        return this.productMongoMapper.toProductMongoDTO(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        this.productMongoService.deleteProduct(id);
    }

}