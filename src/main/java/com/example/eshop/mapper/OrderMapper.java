package com.example.eshop.mapper;

import com.example.eshop.dto.NewOrderDTO;
import com.example.eshop.dto.OrderDTO;
import com.example.eshop.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

    List<OrderDTO> toOrderDTOList(List<Order> orders);

    Order toOrder(OrderDTO orderDTO);

    Order toOrder(NewOrderDTO newOrderDTO);

    List<Order> toOrderList(List<OrderDTO> orderDTOList);

}
