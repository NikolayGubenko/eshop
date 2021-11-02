package com.example.eshop.service;

import com.example.eshop.entity.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage email);

    SimpleMailMessage buildMessage(String token, User user);

}
