package com.example.eshop.controller;

import com.example.eshop.entity.PostalOffice;
import com.example.eshop.service.PostalOfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class PostalOfficeRestController {

    private final PostalOfficeService postalOfficeService;

    public PostalOfficeRestController(PostalOfficeService postalOfficeService) {
        this.postalOfficeService = postalOfficeService;
    }

    @GetMapping("/postaloffice")
    public ResponseEntity<List<PostalOffice>> findAllPostalOffices() {
        return ResponseEntity.ok(this.postalOfficeService.getAllPostalServices());
    }

}
