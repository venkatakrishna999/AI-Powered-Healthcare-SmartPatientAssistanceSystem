package com.vcube.healthcare.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vcube.healthcare.dto.UserRequestDTO;
import com.vcube.healthcare.entity.User;
import com.vcube.healthcare.repository.UserRepository;
import com.vcube.healthcare.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Register User
    @Override
    public User registerUser(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole());

        // Enable the account by default
        user.setEnabled(true);

        return userRepository.save(user);
    }

    // Get User By ID
    @Override
    public User getUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with ID: " + userId));
    }

    // Get User By Username
    @Override
    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username));
    }

    // Get All Users
    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // Update User
    @Override
    public User updateUser(Long userId, User user) {

        User existingUser = getUserById(userId);

        if (user.getUsername() != null) {
            userRepository.findByUsername(user.getUsername())
                    .filter(existing -> !existing.getUserId().equals(userId))
                    .ifPresent(existing -> {
                        throw new RuntimeException("Username already exists");
                    });

            existingUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {
            userRepository.findByEmail(user.getEmail())
                    .filter(existing -> !existing.getUserId().equals(userId))
                    .ifPresent(existing -> {
                        throw new RuntimeException("Email already exists");
                    });

            existingUser.setEmail(user.getEmail());
        }

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(
                    passwordEncoder.encode(user.getPassword()));
        }

        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }

        existingUser.setEnabled(user.getEnabled());

        return userRepository.save(existingUser);
    }

    // Delete User
    @Override
    public void deleteUser(Long userId) {

        User user = getUserById(userId);

        userRepository.delete(user);
    }
}