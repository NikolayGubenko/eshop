package com.example.eshop.service.impl;

import com.example.eshop.entity.Product;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.service.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Product getProduct(long productId) {
        return this.productRepository.getById(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, long productId) throws NotFoundException {
        Product updatedProduct = this.productRepository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setProductType(product.getProductType());

        return this.productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(long productId) {
        this.productRepository.deleteById(productId);
    }
}
