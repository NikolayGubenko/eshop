package com.example.eshop.mapper;

import com.example.eshop.dto.PostalOfficeDTO;
import com.example.eshop.entity.PostalOffice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostalOfficeMapper {

    PostalOfficeDTO toPostalOfficeDTO(PostalOffice postalOffice);

    List<PostalOfficeDTO> toPostalOfficeDTO(List<PostalOffice> postalOffices);

    PostalOffice toPostalOffice (PostalOfficeDTO postalOfficeDTO);

}
