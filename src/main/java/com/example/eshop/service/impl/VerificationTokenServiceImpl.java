package com.example.eshop.service.impl;

import com.example.eshop.entity.VerificationToken;
import com.example.eshop.repository.VerificationTokenRepository;
import com.example.eshop.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken saveToken(VerificationToken verificationToken) {
        return this.verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String verificationToken) {
        return this.verificationTokenRepository.findByToken(verificationToken);
    }
}
