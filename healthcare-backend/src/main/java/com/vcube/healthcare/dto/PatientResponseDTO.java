package com.vcube.healthcare.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PatientResponseDTO {

    private Long patientId;

    private Long userId;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String phoneNumber;

    private String address;

    private String bloodGroup;

    private String emergencyContact;

    private LocalDateTime createdAt;
}