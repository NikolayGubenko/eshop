package com.example.eshop.service.impl;

import com.example.eshop.entity.Role;
import com.example.eshop.entity.User;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(new Role(2L,"USER"));
        return this.userRepository.save(user);
    }
}
