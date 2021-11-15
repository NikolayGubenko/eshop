package com.example.eshop.mapper;

import com.example.eshop.dto.UserDTO;
import com.example.eshop.mysql.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

}
