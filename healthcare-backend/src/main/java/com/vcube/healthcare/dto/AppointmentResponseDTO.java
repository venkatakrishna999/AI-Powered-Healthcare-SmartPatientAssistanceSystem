package com.vcube.healthcare.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentResponseDTO {

    private Long appointmentId;

    private Long patientId;

    private Long doctorId;

    private LocalDateTime appointmentDateTime;

    private String reason;

    private String status;

    private String notes;

    private LocalDateTime createdAt;
}