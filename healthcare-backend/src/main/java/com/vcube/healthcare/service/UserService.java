package com.vcube.healthcare.service;

import java.util.List;

import com.vcube.healthcare.dto.UserRequestDTO;
import com.vcube.healthcare.entity.User;

public interface UserService {

    User registerUser(UserRequestDTO userRequestDTO);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);
}