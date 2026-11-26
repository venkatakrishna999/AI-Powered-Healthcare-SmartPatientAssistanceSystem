package com.vcube.healthcare.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MedicalHistoryRequestDTO {

    private Long patientId;

    private Long doctorId;

    private String conditionName;

    private LocalDate diagnosisDate;

    private String treatment;

    private String medications;

    private String notes;
}
