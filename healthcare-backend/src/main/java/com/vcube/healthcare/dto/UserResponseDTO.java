package com.vcube.healthcare.dto;

import java.time.LocalDateTime;

import com.vcube.healthcare.entity.Role;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long userId;

    private String username;

    private String email;

    private Role role;

    private boolean enabled;

    private LocalDateTime createdAt;
}