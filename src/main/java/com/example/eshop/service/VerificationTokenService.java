package com.example.eshop.service;

import com.example.eshop.mysql.entity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken saveToken(VerificationToken verificationToken);

    VerificationToken findByToken(String verificationToken);

}
