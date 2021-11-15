package com.example.eshop.mapper;

import com.example.eshop.dto.AdminOrderDTO;
import com.example.eshop.dto.OrderDTO;
import com.example.eshop.mysql.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

    AdminOrderDTO toAdminOrderDTO(Order order);

    List<AdminOrderDTO> toAdminOrderDTOList(List<Order> orders);

    List<OrderDTO> toOrderDTOList(List<Order> orders);

    Order toOrder(OrderDTO orderDTO);

    Order toOrder (AdminOrderDTO adminOrderDTO);

}
