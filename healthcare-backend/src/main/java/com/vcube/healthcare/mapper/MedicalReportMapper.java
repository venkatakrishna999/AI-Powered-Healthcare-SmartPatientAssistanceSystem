package com.vcube.healthcare.mapper;

import org.springframework.stereotype.Component;

import com.vcube.healthcare.dto.MedicalReportRequestDTO;
import com.vcube.healthcare.dto.MedicalReportResponseDTO;
import com.vcube.healthcare.entity.MedicalReport;
import com.vcube.healthcare.repository.PatientRepository;
import com.vcube.healthcare.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MedicalReportMapper {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public MedicalReport toEntity(MedicalReportRequestDTO dto) {

        MedicalReport report = new MedicalReport();

        report.setPatient(
            patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"))
        );

        if (dto.getDoctorId() != null) {
            report.setDoctor(
                doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"))
            );
        }

        report.setReportName(dto.getReportName());
        report.setReportType(dto.getReportType());
        report.setDescription(dto.getDescription());
        
        report.setReportDate(dto.getReportDate());

        return report;
    }

    public MedicalReportResponseDTO toResponseDTO(MedicalReport report) {

        MedicalReportResponseDTO dto = new MedicalReportResponseDTO();

        dto.setReportId(report.getReportId());
        dto.setPatientId(report.getPatient().getPatientId());

        if (report.getDoctor() != null) {
            dto.setDoctorId(report.getDoctor().getDoctorId());
        }

        dto.setReportName(report.getReportName());
        dto.setReportType(report.getReportType());
        dto.setDescription(report.getDescription());
        
        dto.setReportDate(report.getReportDate());
        dto.setCreatedAt(report.getCreatedAt());

        return dto;
    }
}