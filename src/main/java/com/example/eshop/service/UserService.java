package com.example.eshop.service;

import com.example.eshop.entity.User;
import com.example.eshop.exception.ShopException;

import java.util.Optional;

public interface UserService {

    User addNewUser(User user);

    Optional<User> findByEmail(String email) throws ShopException;

    User saveUser(User user);

}
