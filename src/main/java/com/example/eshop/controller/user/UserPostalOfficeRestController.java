package com.example.eshop.controller.user;

import com.example.eshop.dto.PageResponseDTO;
import com.example.eshop.dto.PostalOfficeDTO;
import com.example.eshop.mysql.entity.PostalOffice;
import com.example.eshop.mapper.PostalOfficeMapper;
import com.example.eshop.service.PostalOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/users/postal-offices")
public class UserPostalOfficeRestController {

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

    @GetMapping("/{id}")
    public PostalOfficeDTO getPostalOffice(@PathVariable long id) {
        return this.postalOfficeMapper.toPostalOfficeDTO(this.postalOfficeService.getPostalOffice(id));
    }

}
