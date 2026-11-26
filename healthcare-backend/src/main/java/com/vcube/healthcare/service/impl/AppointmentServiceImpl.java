package com.vcube.healthcare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcube.healthcare.dto.AppointmentRequestDTO;
import com.vcube.healthcare.dto.AppointmentResponseDTO;
import com.vcube.healthcare.entity.Appointment;
import com.vcube.healthcare.mapper.AppointmentMapper;
import com.vcube.healthcare.repository.AppointmentRepository;
import com.vcube.healthcare.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repo;
    private final AppointmentMapper mapper;

    // Book Appointment
    @Override
    public AppointmentResponseDTO bookAppointment(
            AppointmentRequestDTO request) {

        Appointment appointment = mapper.toEntity(request);

        Appointment savedAppointment = repo.save(appointment);

        return mapper.toResponseDTO(savedAppointment);
    }

    // Get Appointment By ID
    @Override
    @Transactional(readOnly = true)
    public AppointmentResponseDTO getAppointmentById(Long id) {

        Appointment appointment = find(id);

        return mapper.toResponseDTO(appointment);
    }

    // Get All Appointments
    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getAllAppointments() {

        return repo.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Appointments By Patient ID
    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getPatientAppointments(Long id) {

        return repo.findByPatient_PatientId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Appointments By Doctor ID
    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getDoctorAppointments(Long id) {

        return repo.findByDoctor_DoctorId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Appointments By Status
    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getAppointmentsByStatus(String status) {

        return repo.findByStatus(status)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Update Appointment
    @Override
    public AppointmentResponseDTO updateAppointment(
            Long id, AppointmentRequestDTO request) {

        Appointment oldAppointment = find(id);

        Appointment updatedAppointment = mapper.toEntity(request);

        updatedAppointment.setAppointmentId(id);
        updatedAppointment.setCreatedAt(oldAppointment.getCreatedAt());

        Appointment savedAppointment = repo.save(updatedAppointment);

        return mapper.toResponseDTO(savedAppointment);
    }

    // Delete Appointment
    @Override
    public void deleteAppointment(Long id) {

        Appointment appointment = find(id);

        repo.delete(appointment);
    }

    // Find Appointment
    private Appointment find(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found: " + id));
    }
}