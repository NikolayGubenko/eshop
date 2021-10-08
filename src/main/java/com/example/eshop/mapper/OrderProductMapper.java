package com.example.eshop.mapper;

import com.example.eshop.dto.OrderProductDTO;
import com.example.eshop.entity.OrderProduct;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    Set<OrderProductDTO> toOrderProductDTO(Set<OrderProduct> orderProducts);

}
