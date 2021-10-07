package com.example.eshop.mapper;

import com.example.eshop.dto.OrderProductDTO;
import com.example.eshop.entity.OrderProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    List<OrderProductDTO> toOrderProductDTO(List<OrderProduct> orderProducts);

}
