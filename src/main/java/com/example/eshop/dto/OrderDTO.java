package com.example.eshop.dto;

import com.example.eshop.entity.OrderProduct;
import com.example.eshop.entity.PostalOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderDate;

    private String description;

    private String orderStatus;

    private PostalOffice postalOffice;


}
