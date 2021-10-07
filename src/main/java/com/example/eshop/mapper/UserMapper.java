package com.example.eshop.mapper;

import com.example.eshop.dto.UserDTO;
import com.example.eshop.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

}
