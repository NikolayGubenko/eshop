package com.example.eshop.repository.mongo;

import com.example.eshop.mongo.entity.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMongoRepository extends MongoRepository<ProductMongo, String> {
}
