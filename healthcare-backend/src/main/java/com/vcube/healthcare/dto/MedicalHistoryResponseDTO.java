package com.vcube.healthcare.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MedicalHistoryResponseDTO {

    private Long historyId;

    private Long patientId;

    private Long doctorId;

    private String conditionName;

    private LocalDate diagnosisDate;

    private String treatment;

    private String medications;

    private String notes;

    private LocalDateTime createdAt;
}
