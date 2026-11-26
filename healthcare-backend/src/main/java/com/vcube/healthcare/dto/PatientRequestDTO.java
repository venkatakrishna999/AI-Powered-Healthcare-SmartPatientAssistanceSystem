package com.vcube.healthcare.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PatientRequestDTO {

    private Long userId;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String phoneNumber;

    private String address;

    private String bloodGroup;

    private String emergencyContact;
}
