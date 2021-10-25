package com.example.eshop.service;

import com.example.eshop.entity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken saveToken(VerificationToken verificationToken);

    VerificationToken findByToken(String verificationToken);

}
