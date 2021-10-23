package com.example.eshop.controller.User;

import com.example.eshop.dto.UserDTO;
import com.example.eshop.entity.User;
import com.example.eshop.entity.VerificationToken;
import com.example.eshop.exception.Error;
import com.example.eshop.exception.ShopException;
import com.example.eshop.mapper.UserMapper;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.repository.VerificationTokenRepository;
import com.example.eshop.service.EmailService;
import com.example.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/users/register")
public class UserRegisterRestController {

    private final UserRepository userRepository;

    private final UserService userService;

    private final UserMapper userMapper;

    private final EmailService emailService;

    private final VerificationTokenRepository verificationTokenRepository;

    @PostMapping
    public UserDTO addNewUser(@RequestBody UserDTO userDTO) throws ShopException {

        if (this.userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new ShopException(Error.USER_ALREADY_EXISTS);
        }

        User newUser = this.userService.addNewUser(this.userMapper.toUser(userDTO));

        VerificationToken verificationToken = new VerificationToken(newUser);
        this.verificationTokenRepository.save(verificationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newUser.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("eshopmailerserv@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/confirm-account?token=" + verificationToken.getToken());

        emailService.sendEmail(mailMessage);

        return this.userMapper.toUserDTO(newUser);
    }

}
