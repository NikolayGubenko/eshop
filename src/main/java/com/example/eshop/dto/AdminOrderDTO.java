package com.example.eshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AdminOrderDTO {

    private Long id;

    private Set<OrderProductDTO> orderProducts;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderDate;

    private String orderStatus;

    private boolean active;

}
