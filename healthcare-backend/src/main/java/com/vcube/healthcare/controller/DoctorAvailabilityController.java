package com.vcube.healthcare.controller;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vcube.healthcare.entity.DoctorAvailability;
import com.vcube.healthcare.service.DoctorAvailabilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/doctor-availability")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<DoctorAvailability> addAvailability(
            @RequestBody DoctorAvailability availability) {
        return new ResponseEntity<>(
                availabilityService.addAvailability(availability),
                HttpStatus.CREATED);
    }

    @GetMapping("/{availabilityId}")
    public ResponseEntity<DoctorAvailability> getAvailabilityById(
            @PathVariable Long availabilityId) {
        return ResponseEntity.ok(
                availabilityService.getAvailabilityById(availabilityId));
    }

    @GetMapping
    public ResponseEntity<List<DoctorAvailability>> getAllAvailability() {
        return ResponseEntity.ok(
                availabilityService.getAllAvailability());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<DoctorAvailability>> getDoctorAvailability(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(
                availabilityService.getDoctorAvailability(doctorId));
    }

    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<List<DoctorAvailability>> getAvailabilityByDay(
            @PathVariable DayOfWeek dayOfWeek) {
        return ResponseEntity.ok(
                availabilityService.getAvailabilityByDay(dayOfWeek));
    }

    @GetMapping("/doctor/{doctorId}/day/{dayOfWeek}")
    public ResponseEntity<List<DoctorAvailability>> getDoctorAvailabilityByDay(
            @PathVariable Long doctorId,
            @PathVariable DayOfWeek dayOfWeek) {
        return ResponseEntity.ok(
                availabilityService.getDoctorAvailabilityByDay(
                        doctorId, dayOfWeek));
    }

    @GetMapping("/available")
    public ResponseEntity<List<DoctorAvailability>> getAvailableSchedules() {
        return ResponseEntity.ok(
                availabilityService.getAvailableSchedules());
    }

    @PutMapping("/{availabilityId}")
    public ResponseEntity<DoctorAvailability> updateAvailability(
            @PathVariable Long availabilityId,
            @RequestBody DoctorAvailability availability) {
        return ResponseEntity.ok(
                availabilityService.updateAvailability(
                        availabilityId, availability));
    }

    @DeleteMapping("/{availabilityId}")
    public ResponseEntity<String> deleteAvailability(
            @PathVariable Long availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.ok(
                "Doctor availability deleted successfully");
    }
}