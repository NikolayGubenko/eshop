package com.example.eshop.controller.admin;

import com.example.eshop.dto.PostalOfficeDTO;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.PostalOfficeMapper;
import com.example.eshop.mysql.entity.PostalOffice;
import com.example.eshop.service.PostalOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/postal-offices")
public class AdminPostalOfficeController {

    private final PostalOfficeService postalOfficeService;

    private final PostalOfficeMapper postalOfficeMapper;

    @PostMapping
    public PostalOfficeDTO addPostalOffice(@Valid @RequestBody PostalOfficeDTO postalOfficeDTO) {

        PostalOffice postalOffice = this.postalOfficeService.savePostalOffice(this.postalOfficeMapper.toPostalOffice(postalOfficeDTO));
        return this.postalOfficeMapper.toPostalOfficeDTO(postalOffice);
    }

    @PutMapping("/{id}")
    public PostalOfficeDTO updatePostalOffice(@Valid @RequestBody PostalOfficeDTO postalOfficeDTO,
                                              @PathVariable long id) throws ShopException {

        PostalOffice postalOffice = this.postalOfficeService.updatePostalOffice(this.postalOfficeMapper.toPostalOffice(postalOfficeDTO), id);
        return this.postalOfficeMapper.toPostalOfficeDTO(postalOffice);
    }

    @DeleteMapping("/{id}")
    public void deletePostalOffice(@PathVariable long id) {
        this.postalOfficeService.deletePostalOffice(id);
    }

}
