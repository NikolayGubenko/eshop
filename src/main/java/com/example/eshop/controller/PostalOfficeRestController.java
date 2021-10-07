package com.example.eshop.controller;

import com.example.eshop.entity.PostalOffice;
import com.example.eshop.service.PostalOfficeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/postal-offices")
public class PostalOfficeRestController {

    private final PostalOfficeService postalOfficeService;

    public PostalOfficeRestController(PostalOfficeService postalOfficeService) {
        this.postalOfficeService = postalOfficeService;
    }

    @GetMapping()
    public List<PostalOffice> findAllPostalOffices() {
        return this.postalOfficeService.getAllPostalServices();
    }

}
