package com.example.eshop.service;

import com.example.eshop.entity.Product;
import com.example.eshop.exception.ShopException;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Product getProduct(long productId) throws ShopException;

    Product saveProduct(Product product);

    Product updateProduct(Product product, long productId) throws NotFoundException;

    void deleteProduct(long productId);

}
