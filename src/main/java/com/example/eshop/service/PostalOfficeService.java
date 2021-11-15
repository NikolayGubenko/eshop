package com.example.eshop.service;

import com.example.eshop.mysql.entity.PostalOffice;
import com.example.eshop.exception.ShopException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostalOfficeService {

    Page<PostalOffice> getAllPostalServices(Pageable pageable);

    PostalOffice getPostalOffice(long postalId);

    PostalOffice savePostalOffice(PostalOffice postalOffice);

    PostalOffice updatePostalOffice(PostalOffice postalOffice, long officeId) throws ShopException;

    void deletePostalOffice(long officeId);

}
