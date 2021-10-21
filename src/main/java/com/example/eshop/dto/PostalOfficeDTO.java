package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostalOfficeDTO {

    private Long id;

    @NotBlank(message = "Enter postal office name")
    private String name;

    @NotBlank(message = "Enter postal office address")
    private String address;

}
