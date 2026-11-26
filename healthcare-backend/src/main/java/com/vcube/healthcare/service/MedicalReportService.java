package com.vcube.healthcare.service;
import java.util.List;
import com.vcube.healthcare.dto.MedicalReportRequestDTO;
import com.vcube.healthcare.dto.MedicalReportResponseDTO;
public interface MedicalReportService {
 MedicalReportResponseDTO addMedicalReport(MedicalReportRequestDTO request);
 MedicalReportResponseDTO getReportById(Long id);
 List<MedicalReportResponseDTO> getAllReports();
 List<MedicalReportResponseDTO> getPatientReports(Long patientId);
 List<MedicalReportResponseDTO> getDoctorReports(Long doctorId);
 List<MedicalReportResponseDTO> getReportsByType(String reportType);
 MedicalReportResponseDTO updateReport(Long id, MedicalReportRequestDTO request);
 void deleteReport(Long id);
}
