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

import com.vcube.healthcare.dto.AppointmentRequestDTO;
import com.vcube.healthcare.dto.AppointmentResponseDTO;
import com.vcube.healthcare.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService service;

    // Add Appointment
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> add(
            @RequestBody AppointmentRequestDTO request) {

        return new ResponseEntity<>(
                service.bookAppointment(request),
                HttpStatus.CREATED
        );
    }

    // Get Appointment By ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> one(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getAppointmentById(id)
        );
    }

    // Get All Appointments
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> all() {

        return ResponseEntity.ok(
                service.getAllAppointments()
        );
    }

    // Get Appointments By Patient ID
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> patient(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getPatientAppointments(id)
        );
    }

    // Get Appointments By Doctor ID
    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> doctor(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getDoctorAppointments(id)
        );
    }

    // Get Appointments By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<AppointmentResponseDTO>> status(
            @PathVariable String status) {

        return ResponseEntity.ok(
                service.getAppointmentsByStatus(status)
        );
    }

    // Update Appointment
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody AppointmentRequestDTO request) {

        return ResponseEntity.ok(
                service.updateAppointment(id, request)
        );
    }

    // Delete Appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        service.deleteAppointment(id);

        return ResponseEntity.ok(
                "Appointment deleted successfully"
        );
    }
}