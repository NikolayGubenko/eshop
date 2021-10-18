package com.example.eshop.controller.Admin;

import com.example.eshop.dto.PostalOfficeDTO;
import com.example.eshop.entity.PostalOffice;
import com.example.eshop.mapper.PostalOfficeMapper;
import com.example.eshop.service.PostalOfficeService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/admin/postal-offices")
public class AdminPostalOfficeController {

    private final PostalOfficeService postalOfficeService;

    private final PostalOfficeMapper postalOfficeMapper;

    @GetMapping
    public Page<PostalOfficeDTO> findAllPostalOffices(Pageable pageable) {
        Page<PostalOffice> pagePostalOffices = postalOfficeService.getAllPostalServices(pageable);
        List<PostalOfficeDTO> postalOfficeDTOList = postalOfficeMapper.toPostalOfficeDTO(pagePostalOffices.getContent());
        return new PageImpl<>(postalOfficeDTOList, pageable, pagePostalOffices.getTotalElements());
    }

    @PostMapping
    public PostalOfficeDTO addPostalOffice(@RequestBody PostalOfficeDTO postalOfficeDTO) {
        PostalOffice postalOffice = this.postalOfficeService.savePostalOffice(this.postalOfficeMapper.toPostalOffice(postalOfficeDTO));
        return this.postalOfficeMapper.toPostalOfficeDTO(postalOffice);
    }

    @PutMapping("/{id}")
    public PostalOfficeDTO updatePostalOffice(@RequestBody PostalOfficeDTO postalOfficeDTO,
                                              @PathVariable long id) throws NotFoundException {
        PostalOffice postalOffice=this.postalOfficeService.updatePostalOffice(this.postalOfficeMapper.toPostalOffice(postalOfficeDTO), id);
        return this.postalOfficeMapper.toPostalOfficeDTO(postalOffice);
    }

    @DeleteMapping("/{id}")
    public void deletePostalOffice(@PathVariable long id) {
        this.postalOfficeService.deletePostalOffice(id);
    }

}
