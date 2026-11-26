package com.vcube.healthcare.mapper;

import org.springframework.stereotype.Component;

import com.vcube.healthcare.dto.MedicalHistoryRequestDTO;
import com.vcube.healthcare.dto.MedicalHistoryResponseDTO;
import com.vcube.healthcare.entity.MedicalHistory;
import com.vcube.healthcare.repository.PatientRepository;
import com.vcube.healthcare.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MedicalHistoryMapper {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public MedicalHistory toEntity(MedicalHistoryRequestDTO dto) {

        MedicalHistory history = new MedicalHistory();

        history.setPatient(
            patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"))
        );

        if (dto.getDoctorId() != null) {
            history.setDoctor(
                doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"))
            );
        }

        history.setConditionName(dto.getConditionName());
        history.setDiagnosisDate(dto.getDiagnosisDate());
        history.setTreatment(dto.getTreatment());
        history.setMedications(dto.getMedications());
        history.setNotes(dto.getNotes());

        return history;
    }

    public MedicalHistoryResponseDTO toResponseDTO(MedicalHistory history) {

        MedicalHistoryResponseDTO dto = new MedicalHistoryResponseDTO();

        dto.setHistoryId(history.getHistoryId());
        dto.setPatientId(history.getPatient().getPatientId());

        if (history.getDoctor() != null) {
            dto.setDoctorId(history.getDoctor().getDoctorId());
        }

        dto.setConditionName(history.getConditionName());
        dto.setDiagnosisDate(history.getDiagnosisDate());
        dto.setTreatment(history.getTreatment());
        dto.setMedications(history.getMedications());
        dto.setNotes(history.getNotes());
        dto.setCreatedAt(history.getCreatedAt());

        return dto;
    }
}
