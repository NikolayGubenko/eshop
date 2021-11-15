package com.example.eshop.service.impl.mongo;

import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mongo.entity.ProductMongo;
import com.example.eshop.repository.mongo.ProductMongoRepository;
import com.example.eshop.service.mongo.ProductMongoService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductMongoServiceImpl implements ProductMongoService {

    private final ProductMongoRepository productMongoRepository;

    private List<ProductMongo> convertCsvToEntity(MultipartFile uploadedFile) {
        try {
            return new CsvToBeanBuilder<ProductMongo>(new InputStreamReader(uploadedFile.getInputStream()))
                    .withSeparator(',')
                    .withType(ProductMongo.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException("File convert error:" + e.getMessage());
        }
    }

    @Override
    public void upload(MultipartFile uploadedFile) {
        long diff = 10000;
        List<ProductMongo> productList = this.convertCsvToEntity(uploadedFile);
        productList.forEach(f -> f.setPrice((Math.round(Math.random() * diff))));
        this.productMongoRepository.saveAll(productList);
    }

    @Override
    public ProductMongo getProduct(String id) throws ShopException {
        return this.productMongoRepository.findById(id).orElseThrow(() -> new ShopException(Error.PRODUCT_NOT_FOUND));
    }

    @Override
    public Page<ProductMongo> getAllProducts(Pageable pageable) {
        return this.productMongoRepository.findAll(pageable);
    }

    @Override
    public ProductMongo saveProduct(ProductMongo productMongo) {
        return this.productMongoRepository.save(productMongo);
    }

    @Override
    public ProductMongo updateProduct(ProductMongo productMongo, String productId) throws ShopException {
        ProductMongo updatedProduct = this.productMongoRepository.findById(productMongo.getId())
                .orElseThrow(() -> new ShopException(Error.PRODUCT_NOT_FOUND));

        updatedProduct.setName(productMongo.getName());
        updatedProduct.setPrice(productMongo.getPrice());
        updatedProduct.setDescription(productMongo.getDescription());
        updatedProduct.setProductType(productMongo.getProductType());

        return this.productMongoRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        this.productMongoRepository.deleteById(productId);
    }

}
