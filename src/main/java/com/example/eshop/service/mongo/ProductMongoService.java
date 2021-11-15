package com.example.eshop.service.mongo;

import com.example.eshop.exception.ShopException;
import com.example.eshop.mongo.entity.ProductMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductMongoService {

    void upload(MultipartFile uploadedFile);

    ProductMongo getProduct (String id) throws ShopException;

    Page<ProductMongo> getAllProducts(Pageable pageable);

    ProductMongo saveProduct(ProductMongo productMongo);

    ProductMongo updateProduct(ProductMongo productMongo, String productId) throws ShopException;

    void deleteProduct(String productId);

}
