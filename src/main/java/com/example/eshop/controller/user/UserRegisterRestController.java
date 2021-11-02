package com.example.eshop.controller.user;

import com.example.eshop.dto.UserDTO;
import com.example.eshop.entity.User;
import com.example.eshop.entity.VerificationToken;
import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.UserMapper;
import com.example.eshop.service.EmailService;
import com.example.eshop.service.UserService;
import com.example.eshop.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/users/register")
public class UserRegisterRestController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final VerificationTokenService verificationTokenService;

    private final EmailService emailService;

    @PostMapping
    public UserDTO addNewUser(@RequestBody UserDTO userDTO) throws ShopException {

        if (this.userService.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new ShopException(Error.USER_ALREADY_EXISTS);
        }

        User newUser = this.userService.addNewUser(this.userMapper.toUser(userDTO));
        VerificationToken verificationToken = new VerificationToken(newUser);
        this.verificationTokenService.saveToken(verificationToken);
        emailService.sendEmail(this.emailService.buildMessage(verificationToken.getToken(), newUser));

        return this.userMapper.toUserDTO(newUser);
    }

    @RequestMapping(value = "/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUser(@RequestParam("token") String verificationToken) throws ShopException {

        VerificationToken currentToken = this.verificationTokenService.findByToken(verificationToken);
        if (currentToken != null) {
            User user = userService.findByEmail(currentToken.getUser().getEmail())
                    .orElseThrow(()->new ShopException(Error.USER_NOT_FOUND));
            user.setActive(true);
            this.userService.saveUser(user);
            return new ModelAndView("/confirm");
        } else {
            return new ModelAndView("/denied");
        }
    }

}
