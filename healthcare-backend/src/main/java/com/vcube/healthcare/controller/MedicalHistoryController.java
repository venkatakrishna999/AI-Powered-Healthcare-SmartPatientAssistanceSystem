package com.vcube.healthcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.healthcare.dto.MedicalHistoryRequestDTO;
import com.vcube.healthcare.dto.MedicalHistoryResponseDTO;
import com.vcube.healthcare.service.MedicalHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/medical-history")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedicalHistoryController {

    private final MedicalHistoryService service;

    // Add Medical History
    @PostMapping
    public ResponseEntity<MedicalHistoryResponseDTO> add(
            @RequestBody MedicalHistoryRequestDTO request) {

        return new ResponseEntity<>(
                service.addMedicalHistory(request),
                HttpStatus.CREATED
        );
    }

    // Get Medical History By ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistoryResponseDTO> one(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getHistoryById(id)
        );
    }

    // Get All Medical History
    @GetMapping
    public ResponseEntity<List<MedicalHistoryResponseDTO>> all() {

        return ResponseEntity.ok(
                service.getAllHistory()
        );
    }

    // Get Medical History By Patient ID
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<MedicalHistoryResponseDTO>> patient(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getPatientHistory(id)
        );
    }

    // Get Medical History By Doctor ID
    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<MedicalHistoryResponseDTO>> doctor(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getDoctorHistory(id)
        );
    }

    // Get Medical History By Condition
    @GetMapping("/condition/{name}")
    public ResponseEntity<List<MedicalHistoryResponseDTO>> condition(
            @PathVariable String name) {

        return ResponseEntity.ok(
                service.getHistoryByCondition(name)
        );
    }

    // Update Medical History
    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistoryResponseDTO> update(
            @PathVariable Long id,
            @RequestBody MedicalHistoryRequestDTO request) {

        return ResponseEntity.ok(
                service.updateHistory(id, request)
        );
    }

    // Delete Medical History
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        service.deleteHistory(id);

        return ResponseEntity.ok(
                "Medical history deleted successfully"
        );
    }
}