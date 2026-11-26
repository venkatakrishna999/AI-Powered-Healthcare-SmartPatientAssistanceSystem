package com.vcube.healthcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vcube.healthcare.dto.PatientRequestDTO;
import com.vcube.healthcare.dto.PatientResponseDTO;
import com.vcube.healthcare.entity.Patient;
import com.vcube.healthcare.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    // ADD PATIENT
    @PostMapping
    public ResponseEntity<PatientResponseDTO> addPatient(
            @RequestBody PatientRequestDTO patientRequestDTO) {

        return new ResponseEntity<>(
                patientService.addPatient(patientRequestDTO),
                HttpStatus.CREATED
        );
    }

    // GET PATIENT BY ID
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatientById(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                patientService.getPatientResponseById(patientId)
        );
    }

    // GET PATIENT BY USER ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Patient> getPatientByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                patientService.getPatientByUserId(userId)
        );
    }

    // GET ALL PATIENTS
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {

        return ResponseEntity.ok(
                patientService.getAllPatients()
        );
    }

    // UPDATE PATIENT
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long patientId,
            @RequestBody Patient patient) {

        return ResponseEntity.ok(
                patientService.updatePatient(patientId, patient)
        );
    }

    // DELETE PATIENT
    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deletePatient(
            @PathVariable Long patientId) {

        patientService.deletePatient(patientId);

        return ResponseEntity.ok("Patient deleted successfully");
    }
}