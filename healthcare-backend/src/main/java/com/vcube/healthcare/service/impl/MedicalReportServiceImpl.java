package com.vcube.healthcare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcube.healthcare.dto.MedicalReportRequestDTO;
import com.vcube.healthcare.dto.MedicalReportResponseDTO;
import com.vcube.healthcare.entity.MedicalReport;
import com.vcube.healthcare.mapper.MedicalReportMapper;
import com.vcube.healthcare.repository.MedicalReportRepository;
import com.vcube.healthcare.service.MedicalReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicalReportServiceImpl implements MedicalReportService {

    private final MedicalReportRepository repo;
    private final MedicalReportMapper mapper;

    // Add Medical Report
    @Override
    public MedicalReportResponseDTO addMedicalReport(
            MedicalReportRequestDTO request) {

        MedicalReport report = mapper.toEntity(request);

        MedicalReport savedReport = repo.save(report);

        return mapper.toResponseDTO(savedReport);
    }

    // Get Medical Report By ID
    @Override
    @Transactional(readOnly = true)
    public MedicalReportResponseDTO getReportById(Long id) {

        MedicalReport report = find(id);

        return mapper.toResponseDTO(report);
    }

    // Get All Medical Reports
    @Override
    @Transactional(readOnly = true)
    public List<MedicalReportResponseDTO> getAllReports() {

        return repo.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Medical Reports By Patient ID
    @Override
    @Transactional(readOnly = true)
    public List<MedicalReportResponseDTO> getPatientReports(Long id) {

        return repo.findByPatient_PatientId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Medical Reports By Doctor ID
    @Override
    @Transactional(readOnly = true)
    public List<MedicalReportResponseDTO> getDoctorReports(Long id) {

        return repo.findByDoctor_DoctorId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Medical Reports By Type
    @Override
    @Transactional(readOnly = true)
    public List<MedicalReportResponseDTO> getReportsByType(String type) {

        return repo.findByReportType(type)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Update Medical Report
    @Override
    public MedicalReportResponseDTO updateReport(
            Long id, MedicalReportRequestDTO request) {

        MedicalReport oldReport = find(id);

        MedicalReport updatedReport = mapper.toEntity(request);

        updatedReport.setReportId(id);
        updatedReport.setCreatedAt(oldReport.getCreatedAt());

        MedicalReport savedReport = repo.save(updatedReport);

        return mapper.toResponseDTO(savedReport);
    }

    // Delete Medical Report
    @Override
    public void deleteReport(Long id) {

        MedicalReport report = find(id);

        repo.delete(report);
    }

    // Find Medical Report
    private MedicalReport find(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Medical report not found: " + id));
    }
}