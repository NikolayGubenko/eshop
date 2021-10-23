package com.example.eshop.service.impl;

import com.example.eshop.entity.User;
import com.example.eshop.repository.RoleRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(this.roleRepository.findByName("USER")));
        user.setActive(false);

        return this.userRepository.save(user);
    }
}
