package com.vcube.healthcare.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vcube.healthcare.dto.UserRequestDTO;
import com.vcube.healthcare.dto.UserResponseDTO;
import com.vcube.healthcare.entity.Role;
import com.vcube.healthcare.entity.User;
import com.vcube.healthcare.repository.UserRepository;
import com.vcube.healthcare.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Convert User Entity to UserResponseDTO
    private UserResponseDTO convertToDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setEnabled(Boolean.TRUE.equals(user.getEnabled()));
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    // Find User Entity By ID
    private User findUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: " + userId));
    }

    // Register User
    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

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

        user.setRole(Role.PATIENT);
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    // Get User By ID
    @Override
    public UserResponseDTO getUserById(Long userId) {

        User user = findUserById(userId);

        return convertToDTO(user);
    }

    // Get User By Username
    @Override
    public UserResponseDTO getUserByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username));

        return convertToDTO(user);
    }

    // Get All Users
    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Update User
    @Override
    public UserResponseDTO updateUser(Long userId, User user) {

        User existingUser = findUserById(userId);

        if (user.getUsername() != null) {

            userRepository.findByUsername(user.getUsername())
                    .filter(existing ->
                            !existing.getUserId().equals(userId))
                    .ifPresent(existing -> {
                        throw new RuntimeException(
                                "Username already exists");
                    });

            existingUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {

            userRepository.findByEmail(user.getEmail())
                    .filter(existing ->
                            !existing.getUserId().equals(userId))
                    .ifPresent(existing -> {
                        throw new RuntimeException(
                                "Email already exists");
                    });

            existingUser.setEmail(user.getEmail());
        }

        if (user.getPassword() != null &&
                !user.getPassword().isBlank()) {

            existingUser.setPassword(
                    passwordEncoder.encode(user.getPassword()));
        }

        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }

        if (user.getEnabled() != null) {
            existingUser.setEnabled(user.getEnabled());
        }

        User updatedUser = userRepository.save(existingUser);

        return convertToDTO(updatedUser);
    }

    // Delete User
    @Override
    public void deleteUser(Long userId) {

        User user = findUserById(userId);

        userRepository.delete(user);
    }
}
