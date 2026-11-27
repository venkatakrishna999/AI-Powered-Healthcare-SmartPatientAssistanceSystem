package com.vcube.healthcare.service;

import java.util.List;

import com.vcube.healthcare.dto.UserRequestDTO;
import com.vcube.healthcare.dto.UserResponseDTO;
import com.vcube.healthcare.entity.User;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long userId);

    UserResponseDTO getUserByUsername(String username);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long userId, User user);

    void deleteUser(Long userId);
}
