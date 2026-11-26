package com.vcube.healthcare.service;

import java.util.List;

import com.vcube.healthcare.dto.DoctorResponseDTO;
import com.vcube.healthcare.entity.Doctor;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);

    DoctorResponseDTO getDoctorById(Long doctorId);

    Doctor getDoctorByUserId(Long userId);

    List<DoctorResponseDTO> getAllDoctors();

    List<Doctor> getDoctorsBySpecialization(String specialization);

    List<Doctor> getAvailableDoctors();

    DoctorResponseDTO updateDoctor(Long doctorId, Doctor doctor);
    
    void deleteDoctor(Long doctorId);
}