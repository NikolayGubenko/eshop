package com.example.eshop.service;

import com.example.eshop.entity.Product;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Product getProduct(long productId);

    void saveProduct(Product product);

    void updateProduct(Product product, long productId) throws NotFoundException;

    void deleteProduct(long productId);

}
