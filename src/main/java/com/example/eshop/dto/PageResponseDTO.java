package com.example.eshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<T> {

    private List<T> content;

    private long page;

    private long total;

    private long records;

}
