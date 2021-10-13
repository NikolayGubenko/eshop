package com.example.eshop.controller;

import com.example.eshop.mapper.PostalOfficeMapper;
import com.example.eshop.service.PostalOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/postal-offices")
public class PostalOfficeRestController {

    private final PostalOfficeService postalOfficeService;

    private final PostalOfficeMapper postalOfficeMapper;

}
