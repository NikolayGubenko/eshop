package com.example.eshop.service.impl;

import com.example.eshop.mysql.entity.User;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = this.userRepository.findUserByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        return new CustomUserDetails(user);
    }
}
