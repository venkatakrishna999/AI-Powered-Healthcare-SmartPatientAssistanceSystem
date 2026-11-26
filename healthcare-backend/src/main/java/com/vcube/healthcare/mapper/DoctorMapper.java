package com.vcube.healthcare.mapper;

import org.springframework.stereotype.Component;

import com.vcube.healthcare.dto.DoctorRequestDTO;
import com.vcube.healthcare.dto.DoctorResponseDTO;
import com.vcube.healthcare.entity.Doctor;
import com.vcube.healthcare.entity.User;
import com.vcube.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DoctorMapper {

    private final UserRepository userRepository;

    public Doctor toEntity(DoctorRequestDTO dto) {

        Doctor doctor = new Doctor();

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        doctor.setUser(user);
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setQualification(dto.getQualification());
        doctor.setExperienceYears(dto.getExperienceYears());
        doctor.setConsultationFee(dto.getConsultationFee());
        doctor.setHospitalName(dto.getHospitalName());
        doctor.setBio(dto.getBio());
        doctor.setAvailable(dto.isAvailable());

        return doctor;
    }

    public DoctorResponseDTO toResponseDTO(Doctor doctor) {

        DoctorResponseDTO dto = new DoctorResponseDTO();

        dto.setDoctorId(doctor.getDoctorId());
        dto.setUserId(doctor.getUser().getUserId());
        dto.setUsername(doctor.getUser().getUsername());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setQualification(doctor.getQualification());
        dto.setExperienceYears(doctor.getExperienceYears());
        dto.setConsultationFee(doctor.getConsultationFee());
        dto.setHospitalName(doctor.getHospitalName());
        dto.setBio(doctor.getBio());
        dto.setAvailable(doctor.getAvailable());

        return dto;
    }
}