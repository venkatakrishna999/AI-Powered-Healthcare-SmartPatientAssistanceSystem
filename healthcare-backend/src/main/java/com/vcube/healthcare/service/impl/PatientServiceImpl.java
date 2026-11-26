package com.vcube.healthcare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcube.healthcare.dto.PatientRequestDTO;
import com.vcube.healthcare.dto.PatientResponseDTO;
import com.vcube.healthcare.entity.Patient;
import com.vcube.healthcare.entity.User;
import com.vcube.healthcare.repository.PatientRepository;
import com.vcube.healthcare.repository.UserRepository;
import com.vcube.healthcare.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    // ADD PATIENT
    @Override
 
    public PatientResponseDTO addPatient(PatientRequestDTO dto) {

        Patient patient = new Patient();

        // USER ACCOUNT IS OPTIONAL
        if (dto.getUserId() != null) {

            Long userId = dto.getUserId();

            if (patientRepository.existsByUser_UserId(userId)) {
                throw new RuntimeException("Patient profile already exists");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            patient.setUser(user);
        }

        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setEmergencyContact(dto.getEmergencyContact());

        Patient savedPatient = patientRepository.save(patient);

        return convertToDTO(savedPatient);
    }

    // GET PATIENT BY ID
    @Override
    @Transactional(readOnly = true)
    public Patient getPatientById(Long patientId) {

        return patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // GET PATIENT RESPONSE BY ID
    @Override
    @Transactional(readOnly = true)
    public PatientResponseDTO getPatientResponseById(Long patientId) {

        Patient patient = getPatientById(patientId);

        return convertToDTO(patient);
    }

    // GET PATIENT BY USER ID
    @Override
    @Transactional(readOnly = true)
    public Patient getPatientByUserId(Long userId) {

        return patientRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // GET ALL PATIENTS
    @Override
    @Transactional(readOnly = true)
    public List<PatientResponseDTO> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

 // CONVERT PATIENT ENTITY TO DTO
    private PatientResponseDTO convertToDTO(Patient patient) {

        PatientResponseDTO response = new PatientResponseDTO();

        response.setPatientId(patient.getPatientId());

        if (patient.getUser() != null) {
            response.setUserId(patient.getUser().getUserId());
            response.setUsername(patient.getUser().getUsername());
            response.setEmail(patient.getUser().getEmail());
        }

        response.setFirstName(patient.getFirstName());
        response.setLastName(patient.getLastName());
        response.setDateOfBirth(patient.getDateOfBirth());
        response.setGender(patient.getGender());
        response.setPhoneNumber(patient.getPhoneNumber());
        response.setAddress(patient.getAddress());
        response.setBloodGroup(patient.getBloodGroup());
        response.setEmergencyContact(patient.getEmergencyContact());
        response.setCreatedAt(patient.getCreatedAt());

        return response;
    }

    // UPDATE PATIENT
    @Override
    public PatientResponseDTO updatePatient(Long patientId, Patient patient) {

        Patient existing = getPatientById(patientId);

        existing.setFirstName(patient.getFirstName());
        existing.setLastName(patient.getLastName());
        existing.setDateOfBirth(patient.getDateOfBirth());
        existing.setGender(patient.getGender());
        existing.setPhoneNumber(patient.getPhoneNumber());
        existing.setAddress(patient.getAddress());
        existing.setBloodGroup(patient.getBloodGroup());
        existing.setEmergencyContact(patient.getEmergencyContact());

        Patient updatedPatient = patientRepository.save(existing);

        return convertToDTO(updatedPatient);
    }

    // DELETE PATIENT
    @Override
    public void deletePatient(Long patientId) {

        Patient patient = getPatientById(patientId);

        patientRepository.delete(patient);
    }
}