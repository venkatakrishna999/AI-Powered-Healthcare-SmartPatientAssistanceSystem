package com.vcube.healthcare.dto;

import com.vcube.healthcare.entity.Role;

import lombok.Data;

@Data
public class UserRequestDTO {

    private String username;

    private String email;

    private String password;

    private Role role;
}