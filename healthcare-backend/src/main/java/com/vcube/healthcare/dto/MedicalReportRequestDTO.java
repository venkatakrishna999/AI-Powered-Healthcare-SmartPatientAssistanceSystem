package com.vcube.healthcare.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MedicalReportRequestDTO {

    private Long patientId;

    private Long doctorId;

    private String reportName;

    private String reportType;

    private String description;

   

    private LocalDate reportDate;
}