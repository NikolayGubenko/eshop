package com.example.eshop.service.impl;

import com.example.eshop.entity.User;
import com.example.eshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Override
    public SimpleMailMessage buildMessage(String token, User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("eshopmailerserv@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/api/v1/users/register/confirm?token=" + token);

        return mailMessage;
    }

}
