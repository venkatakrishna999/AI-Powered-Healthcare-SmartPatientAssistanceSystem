package com.vcube.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.healthcare.entity.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

    List<MedicalHistory> findByPatient_PatientId(Long patientId);

    List<MedicalHistory> findByDoctor_DoctorId(Long doctorId);

    List<MedicalHistory> findByConditionName(String conditionName);
}