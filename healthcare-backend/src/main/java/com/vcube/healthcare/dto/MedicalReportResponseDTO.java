package com.vcube.healthcare.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MedicalReportResponseDTO {

    private Long reportId;

    private Long patientId;

    private Long doctorId;

    private String reportName;

    private String reportType;

    private String description;

    private String fileUrl;

    private LocalDate reportDate;

    private LocalDateTime createdAt;
}