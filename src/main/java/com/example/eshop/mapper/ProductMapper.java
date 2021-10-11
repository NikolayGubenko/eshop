package com.example.eshop.mapper;

import com.example.eshop.dto.ProductDTO;
import com.example.eshop.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDTO> toProductDTOList(List<Product> products);

    ProductDTO toProductDTO (Product product);

}
