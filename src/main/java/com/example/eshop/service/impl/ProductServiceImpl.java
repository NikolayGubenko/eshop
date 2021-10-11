package com.example.eshop.service.impl;

import com.example.eshop.entity.Product;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProduct(long productId) {
        return this.productRepository.getById(productId);
    }
}
