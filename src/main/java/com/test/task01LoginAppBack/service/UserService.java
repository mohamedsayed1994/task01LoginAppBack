package com.test.task01LoginAppBack.service;

import com.test.task01LoginAppBack.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User checkUser(String username, String password);

    User create(User user);

    List<User> findAll();

    User findById(Long id);
}
