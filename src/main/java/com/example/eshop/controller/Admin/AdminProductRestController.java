package com.example.eshop.controller.Admin;

import com.example.eshop.dto.ProductDTO;
import com.example.eshop.entity.Product;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.service.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/products")
public class AdminProductRestController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public Map<String, Object> findAllProducts(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int rows) {
        Pageable paging = PageRequest.of(page, rows);
        Page<Product> pageProducts = productService.getAllProducts(paging);
        List<ProductDTO> productDTOList = productMapper.toProductDTOList(pageProducts.getContent());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("content", productDTOList);
        response.put("page", pageProducts.getNumber());
        response.put("total", pageProducts.getTotalPages());
        response.put("records", pageProducts.getTotalElements());
        return response;
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = this.productService.saveProduct(this.productMapper.toProduct(productDTO));
        return this.productMapper.toProductDTO(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO updatedProduct,
                                    @PathVariable long id) throws NotFoundException {
        Product product = this.productService.updateProduct(this.productMapper.toProduct(updatedProduct), id);
        return this.productMapper.toProductDTO(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        this.productService.deleteProduct(id);
    }

}
