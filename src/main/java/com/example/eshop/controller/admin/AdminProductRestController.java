package com.example.eshop.controller.admin;

import com.example.eshop.dto.PageResponseDTO;
import com.example.eshop.dto.ProductDTO;
import com.example.eshop.entity.Product;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.service.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/products")
public class AdminProductRestController {

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

    @PostMapping
    public ProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = this.productService.saveProduct(this.productMapper.toProduct(productDTO));
        return this.productMapper.toProductDTO(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@Valid @RequestBody ProductDTO updatedProduct,
                                    @PathVariable long id) throws NotFoundException {

        Product product = this.productService.updateProduct(this.productMapper.toProduct(updatedProduct), id);
        return this.productMapper.toProductDTO(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        this.productService.deleteProduct(id);
    }

}
