package com.vcube.healthcare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcube.healthcare.dto.MedicalHistoryRequestDTO;
import com.vcube.healthcare.dto.MedicalHistoryResponseDTO;
import com.vcube.healthcare.entity.MedicalHistory;
import com.vcube.healthcare.mapper.MedicalHistoryMapper;
import com.vcube.healthcare.repository.MedicalHistoryRepository;
import com.vcube.healthcare.service.MedicalHistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository repo;
    private final MedicalHistoryMapper mapper;

    // Add Medical History
    @Override
    public MedicalHistoryResponseDTO addMedicalHistory(
            MedicalHistoryRequestDTO request) {

        MedicalHistory history = mapper.toEntity(request);

        MedicalHistory savedHistory = repo.save(history);

        return mapper.toResponseDTO(savedHistory);
    }

    // Get Medical History By ID
    @Override
    @Transactional(readOnly = true)
    public MedicalHistoryResponseDTO getHistoryById(Long id) {

        MedicalHistory history = find(id);

        return mapper.toResponseDTO(history);
    }

    // Get All Medical History
    @Override
    @Transactional(readOnly = true)
    public List<MedicalHistoryResponseDTO> getAllHistory() {

        return repo.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Medical History By Patient ID
    @Override
    @Transactional(readOnly = true)
    public List<MedicalHistoryResponseDTO> getPatientHistory(Long id) {

        return repo.findByPatient_PatientId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Medical History By Doctor ID
    @Override
    @Transactional(readOnly = true)
    public List<MedicalHistoryResponseDTO> getDoctorHistory(Long id) {

        return repo.findByDoctor_DoctorId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Get Medical History By Condition
    @Override
    @Transactional(readOnly = true)
    public List<MedicalHistoryResponseDTO> getHistoryByCondition(String name) {

        return repo.findByConditionName(name)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // Update Medical History
    @Override
    public MedicalHistoryResponseDTO updateHistory(
            Long id, MedicalHistoryRequestDTO request) {

        MedicalHistory oldHistory = find(id);

        MedicalHistory updatedHistory = mapper.toEntity(request);

        updatedHistory.setHistoryId(id);
        updatedHistory.setCreatedAt(oldHistory.getCreatedAt());

        MedicalHistory savedHistory = repo.save(updatedHistory);

        return mapper.toResponseDTO(savedHistory);
    }

    // Delete Medical History
    @Override
    public void deleteHistory(Long id) {

        MedicalHistory history = find(id);

        repo.delete(history);
    }

    // Find Medical History
    private MedicalHistory find(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Medical history not found: " + id));
    }
}