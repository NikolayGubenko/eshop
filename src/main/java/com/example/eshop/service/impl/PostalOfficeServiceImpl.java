package com.example.eshop.service.impl;

import com.example.eshop.entity.PostalOffice;
import com.example.eshop.repository.PostalOfficeRepository;
import com.example.eshop.service.PostalOfficeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostalOfficeServiceImpl implements PostalOfficeService {

    private final PostalOfficeRepository postalOfficeRepository;

    public PostalOfficeServiceImpl(PostalOfficeRepository postalOfficeRepository) {
        this.postalOfficeRepository = postalOfficeRepository;
    }

    @Override
    public List<PostalOffice> getAllPostalServices() {
        return postalOfficeRepository.findAll();
    }
}
