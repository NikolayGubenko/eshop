package com.example.eshop.service.impl;

import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mysql.entity.Product;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.service.ProductService;
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
    public Product getProduct(long productId) throws ShopException {
        return this.productRepository.findById(productId).orElseThrow(() -> new ShopException(Error.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, long productId) throws ShopException {
        Product updatedProduct = this.productRepository.findById(product.getId())
                .orElseThrow(() -> new ShopException(Error.PRODUCT_NOT_FOUND));

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
