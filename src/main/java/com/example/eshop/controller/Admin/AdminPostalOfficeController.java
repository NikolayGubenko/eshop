package com.example.eshop.controller.Admin;

import com.example.eshop.dto.PageResponseDTO;
import com.example.eshop.dto.PostalOfficeDTO;
import com.example.eshop.entity.PostalOffice;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.PostalOfficeMapper;
import com.example.eshop.service.PostalOfficeService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/postal-offices")
public class AdminPostalOfficeController {

    private final PostalOfficeService postalOfficeService;

    private final PostalOfficeMapper postalOfficeMapper;

    @GetMapping
    public PageResponseDTO<PostalOfficeDTO> findAllPostalOffices(@RequestParam(required = false, defaultValue = "1") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int rows) {

        PageResponseDTO<PostalOfficeDTO> pageResponse = new PageResponseDTO<>();
        Page<PostalOffice> pagePostal = this.postalOfficeService.getAllPostalServices(PageRequest.of(Math.decrementExact(page), rows));
        List<PostalOfficeDTO> postalDTOList = this.postalOfficeMapper.toPostalOfficeDTOList(pagePostal.getContent());
        pageResponse.setContent(postalDTOList);
        pageResponse.setPage(Math.incrementExact(pagePostal.getNumber()));
        pageResponse.setTotal(pagePostal.getTotalPages());
        pageResponse.setRecords(pagePostal.getTotalElements());

        return pageResponse;
    }

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
