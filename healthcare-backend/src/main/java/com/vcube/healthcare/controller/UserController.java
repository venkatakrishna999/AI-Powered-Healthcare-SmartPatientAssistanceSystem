package com.vcube.healthcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vcube.healthcare.dto.UserRequestDTO;
import com.vcube.healthcare.dto.UserResponseDTO;
import com.vcube.healthcare.entity.User;
import com.vcube.healthcare.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(
            @RequestBody UserRequestDTO userRequestDTO) {

        return new ResponseEntity<>(
                userService.registerUser(userRequestDTO),
                HttpStatus.CREATED);
    }

    // Get User By ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                userService.getUserById(userId));
    }

    // Get User By Username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(
                userService.getUserByUsername(username));
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers());
    }

    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long userId,
            @RequestBody User user) {

        return ResponseEntity.ok(
                userService.updateUser(userId, user));
    }

    // Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.ok("User deleted successfully");
    }
}
