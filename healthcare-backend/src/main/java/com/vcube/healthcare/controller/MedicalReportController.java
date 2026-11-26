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

import com.vcube.healthcare.dto.MedicalReportRequestDTO;
import com.vcube.healthcare.dto.MedicalReportResponseDTO;
import com.vcube.healthcare.service.MedicalReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedicalReportController {

    private final MedicalReportService service;

    // Add Medical Report
    @PostMapping
    public ResponseEntity<MedicalReportResponseDTO> add(
            @RequestBody MedicalReportRequestDTO request) {

        return new ResponseEntity<>(
                service.addMedicalReport(request),
                HttpStatus.CREATED
        );
    }

    // Get Medical Report By ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalReportResponseDTO> one(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getReportById(id)
        );
    }

    // Get All Medical Reports
    @GetMapping
    public ResponseEntity<List<MedicalReportResponseDTO>> all() {

        return ResponseEntity.ok(
                service.getAllReports()
        );
    }

    // Get Medical Reports By Patient ID
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<MedicalReportResponseDTO>> patient(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getPatientReports(id)
        );
    }

    // Get Medical Reports By Doctor ID
    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<MedicalReportResponseDTO>> doctor(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getDoctorReports(id)
        );
    }

    // Get Medical Reports By Type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<MedicalReportResponseDTO>> type(
            @PathVariable String type) {

        return ResponseEntity.ok(
                service.getReportsByType(type)
        );
    }

    // Update Medical Report
    @PutMapping("/{id}")
    public ResponseEntity<MedicalReportResponseDTO> update(
            @PathVariable Long id,
            @RequestBody MedicalReportRequestDTO request) {

        return ResponseEntity.ok(
                service.updateReport(id, request)
        );
    }

    // Delete Medical Report
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        service.deleteReport(id);

        return ResponseEntity.ok(
                "Medical report deleted successfully"
        );
    }
}