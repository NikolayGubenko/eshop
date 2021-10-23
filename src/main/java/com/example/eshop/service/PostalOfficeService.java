package com.example.eshop.service;

import com.example.eshop.entity.PostalOffice;
import com.example.eshop.entity.Product;
import com.example.eshop.exception.ShopException;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostalOfficeService {

    Page<PostalOffice> getAllPostalServices(Pageable pageable);

    PostalOffice getPostalOffice(long postalId);

    PostalOffice savePostalOffice(PostalOffice postalOffice);

    PostalOffice updatePostalOffice(PostalOffice postalOffice, long officeId) throws ShopException;

    void deletePostalOffice(long officeId);

}
