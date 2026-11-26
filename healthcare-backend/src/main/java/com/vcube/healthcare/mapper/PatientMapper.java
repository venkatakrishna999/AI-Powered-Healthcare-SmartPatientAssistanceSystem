package com.vcube.healthcare.mapper;

import org.springframework.stereotype.Component;

import com.vcube.healthcare.dto.PatientRequestDTO;
import com.vcube.healthcare.dto.PatientResponseDTO;
import com.vcube.healthcare.entity.Patient;
import com.vcube.healthcare.entity.User;
import com.vcube.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final UserRepository userRepository;

    // Convert DTO into Entity
    public Patient toEntity(PatientRequestDTO dto) {

        Patient patient = new Patient();

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        patient.setUser(user);
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setEmergencyContact(dto.getEmergencyContact());

        return patient;
    }

    // Convert Entity into Response DTO
    public PatientResponseDTO toResponseDTO(Patient patient) {

        PatientResponseDTO dto = new PatientResponseDTO();

        dto.setPatientId(patient.getPatientId());

        if (patient.getUser() != null) {
            dto.setUserId(patient.getUser().getUserId());
            dto.setUsername(patient.getUser().getUsername());
            dto.setEmail(patient.getUser().getEmail());
        }

        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setAddress(patient.getAddress());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setEmergencyContact(patient.getEmergencyContact());
        dto.setCreatedAt(patient.getCreatedAt());

        return dto;
    }
}