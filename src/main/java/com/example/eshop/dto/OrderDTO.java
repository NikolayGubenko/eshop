package com.example.eshop.dto;

import com.example.eshop.entity.PostalOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDTO {

    private Long id;

    private Set<OrderProductDTO> orderProducts;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderDate;

    private String description;

    private String orderStatus;

    private PostalOffice postalOffice;

}
