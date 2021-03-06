package com.example.eshop.service.impl;

import com.example.eshop.entity.PostalOffice;
import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.repository.PostalOfficeRepository;
import com.example.eshop.service.PostalOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostalOfficeServiceImpl implements PostalOfficeService {

    private final PostalOfficeRepository postalOfficeRepository;

    @Override
    public Page<PostalOffice> getAllPostalServices(Pageable pageable) {
        return this.postalOfficeRepository.findAll(pageable);
    }

    @Override
    public PostalOffice getPostalOffice(long postalId) {
        return this.postalOfficeRepository.getById(postalId);
    }

    @Override
    public PostalOffice savePostalOffice(PostalOffice postalOffice) {
        return this.postalOfficeRepository.save(postalOffice);
    }

    @Override
    public PostalOffice updatePostalOffice(PostalOffice postalOffice, long officeId) throws ShopException {
        PostalOffice updatedPostal = this.postalOfficeRepository.findById(postalOffice.getId())
                .orElseThrow(() -> new ShopException(Error.POSTAL_OFFICE_NOT_FOUND));

        updatedPostal.setName(postalOffice.getName());
        updatedPostal.setAddress(postalOffice.getAddress());

        return this.postalOfficeRepository.save(updatedPostal);
    }

    @Override
    public void deletePostalOffice(long officeId) {
        this.postalOfficeRepository.deleteById(officeId);
    }

}
