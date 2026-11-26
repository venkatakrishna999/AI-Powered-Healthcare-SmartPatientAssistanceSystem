package com.vcube.healthcare.dto;

import lombok.Data;

@Data
public class DoctorRequestDTO {

    private Long userId;

    private String specialization;

    private String qualification;

    private Integer experienceYears;

    private Double consultationFee;

    private String hospitalName;

    private String bio;

    private boolean available;
}