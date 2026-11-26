package com.vcube.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.healthcare.entity.MedicalReport;

public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {

    List<MedicalReport> findByPatient_PatientId(Long patientId);

    List<MedicalReport> findByDoctor_DoctorId(Long doctorId);

    List<MedicalReport> findByReportType(String reportType);
}