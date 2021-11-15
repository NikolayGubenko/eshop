package com.example.eshop.mapper.mongo;

import com.example.eshop.dto.mongo.ProductMongoDTO;
import com.example.eshop.mongo.entity.ProductMongo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMongoMapper {

    List<ProductMongoDTO> toProductMongoDTOList(List<ProductMongo> productList);

    ProductMongoDTO toProductMongoDTO(ProductMongo productMongo);

    ProductMongo toProductMongo(ProductMongoDTO productMongoDTO);

}
