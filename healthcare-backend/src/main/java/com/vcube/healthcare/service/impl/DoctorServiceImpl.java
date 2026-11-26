package com.vcube.healthcare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcube.healthcare.dto.DoctorResponseDTO;
import com.vcube.healthcare.entity.Doctor;
import com.vcube.healthcare.entity.User;
import com.vcube.healthcare.repository.DoctorRepository;
import com.vcube.healthcare.repository.UserRepository;
import com.vcube.healthcare.service.DoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    // CONVERT DOCTOR TO DTO
    private DoctorResponseDTO convertToDTO(Doctor doctor) {

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

    // ADD DOCTOR
    @Override
    public Doctor addDoctor(Doctor doctor) {

        if (doctor.getUser() == null ||
                doctor.getUser().getUserId() == null) {
            throw new RuntimeException("User ID is required");
        }

        Long userId = doctor.getUser().getUserId();

        if (doctorRepository.existsByUser_UserId(userId)) {
            throw new RuntimeException("Doctor profile already exists");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        doctor.setUser(user);

        return doctorRepository.save(doctor);
    }

 // GET DOCTOR BY ID
    @Override
    @Transactional(readOnly = true)
    public DoctorResponseDTO getDoctorById(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return convertToDTO(doctor);
    }

    // GET DOCTOR BY USER ID
    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctorByUserId(Long userId) {

        return doctorRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // GET ALL DOCTORS
    @Override
    @Transactional(readOnly = true)
    public List<DoctorResponseDTO> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // GET DOCTORS BY SPECIALIZATION
    @Override
    @Transactional(readOnly = true)
    public List<Doctor> getDoctorsBySpecialization(String specialization) {

        return doctorRepository.findBySpecialization(specialization);
    }

    // GET AVAILABLE DOCTORS
    @Override
    @Transactional(readOnly = true)
    public List<Doctor> getAvailableDoctors() {

        return doctorRepository.findByAvailableTrue();
    }

 // UPDATE DOCTOR
    @Override
    public DoctorResponseDTO updateDoctor(Long doctorId, Doctor doctor) {

        Doctor existing = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existing.setSpecialization(doctor.getSpecialization());
        existing.setQualification(doctor.getQualification());
        existing.setExperienceYears(doctor.getExperienceYears());
        existing.setConsultationFee(doctor.getConsultationFee());
        existing.setHospitalName(doctor.getHospitalName());
        existing.setBio(doctor.getBio());

        existing.setAvailable(
                doctor.getAvailable() != null
                        ? doctor.getAvailable()
                        : true
        );

        Doctor updatedDoctor = doctorRepository.save(existing);

        return convertToDTO(updatedDoctor);
    }

 // DELETE DOCTOR
    @Override
    public void deleteDoctor(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorRepository.delete(doctor);
    }
}