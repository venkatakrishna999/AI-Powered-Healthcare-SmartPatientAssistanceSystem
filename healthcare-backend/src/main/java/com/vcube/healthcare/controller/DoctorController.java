package com.vcube.healthcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vcube.healthcare.entity.Doctor;
import com.vcube.healthcare.service.DoctorService;
import com.vcube.healthcare.dto.DoctorResponseDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(
                doctorService.addDoctor(doctor), HttpStatus.CREATED);
    }
 // GET DOCTOR BY ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Doctor> getDoctorByUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok(doctorService.getDoctorByUserId(userId));
    }
 // GET ALL DOCTORS
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {

        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(
            @PathVariable String specialization) {
        return ResponseEntity.ok(
                doctorService.getDoctorsBySpecialization(specialization));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Doctor>> getAvailableDoctors() {
        return ResponseEntity.ok(doctorService.getAvailableDoctors());
    }
 // UPDATE DOCTOR
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(
            @PathVariable Long doctorId,
            @RequestBody Doctor doctor) {

        return ResponseEntity.ok(
                doctorService.updateDoctor(doctorId, doctor)
        );
    }
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
}