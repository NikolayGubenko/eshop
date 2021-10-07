package com.example.eshop.dto;

import com.example.eshop.entity.OrderProduct;
import com.example.eshop.entity.PostalOffice;
import com.example.eshop.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class NewOrderDTO {

    private List<OrderProduct> orderProducts;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private String description;

    private String orderStatus;

    private PostalOffice postalOffice;

    private User user;

}
