package com.example.eshop.service;

import com.example.eshop.exception.ShopException;
import com.example.eshop.mysql.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Product getProduct(long productId) throws ShopException;

    Product saveProduct(Product product);

    Product updateProduct(Product product, long productId) throws ShopException;

    void deleteProduct(long productId);

}
