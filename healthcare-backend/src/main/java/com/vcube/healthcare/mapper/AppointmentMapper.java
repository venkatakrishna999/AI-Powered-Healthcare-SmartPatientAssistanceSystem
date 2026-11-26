package com.vcube.healthcare.mapper;

import org.springframework.stereotype.Component;

import com.vcube.healthcare.dto.AppointmentRequestDTO;
import com.vcube.healthcare.dto.AppointmentResponseDTO;
import com.vcube.healthcare.entity.Appointment;
import com.vcube.healthcare.repository.PatientRepository;
import com.vcube.healthcare.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public Appointment toEntity(AppointmentRequestDTO dto) {

        Appointment appointment = new Appointment();

        appointment.setPatient(
            patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"))
        );

        appointment.setDoctor(
            doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"))
        );

        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setReason(dto.getReason());
        if (dto.getStatus() != null && !dto.getStatus().isBlank()) appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());
        if (dto.getStatus() != null && !dto.getStatus().isBlank()) appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());

        return appointment;
    }

    public AppointmentResponseDTO toResponseDTO(Appointment appointment) {

        AppointmentResponseDTO dto = new AppointmentResponseDTO();

        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setPatientId(appointment.getPatient().getPatientId());
        dto.setDoctorId(appointment.getDoctor().getDoctorId());
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());
        dto.setReason(appointment.getReason());
        dto.setStatus(appointment.getStatus());
        dto.setNotes(appointment.getNotes());
        dto.setCreatedAt(appointment.getCreatedAt());

        return dto;
    }
}
