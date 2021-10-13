package com.example.eshop.service;

import com.example.eshop.entity.PostalOffice;
import com.example.eshop.entity.Product;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostalOfficeService {

    Page<PostalOffice> getAllPostalServices(Pageable pageable);

    PostalOffice getPostalOffice(long postalId);

    void savePostalOffice(PostalOffice postalOffice);

    void updatePostalOffice(PostalOffice postalOffice, long officeId) throws NotFoundException;

    void deletePostalOffice(long officeId);

}
