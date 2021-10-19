package com.example.eshop.controller.User;

import com.example.eshop.dto.UserDTO;
import com.example.eshop.entity.User;
import com.example.eshop.mapper.UserMapper;
import com.example.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/users")
public class UserRestController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    public UserDTO addNewUser(@RequestBody UserDTO userDTO) {
        User newUser=this.userService.addNewUser(this.userMapper.toUser(userDTO));
        return this.userMapper.toUserDTO(newUser);
    }

}
