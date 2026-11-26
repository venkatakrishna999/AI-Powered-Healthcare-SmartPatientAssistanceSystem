package com.vcube.healthcare.service;
import java.util.List;
import com.vcube.healthcare.dto.MedicalHistoryRequestDTO;
import com.vcube.healthcare.dto.MedicalHistoryResponseDTO;
public interface MedicalHistoryService {
 MedicalHistoryResponseDTO addMedicalHistory(MedicalHistoryRequestDTO request);
 MedicalHistoryResponseDTO getHistoryById(Long id);
 List<MedicalHistoryResponseDTO> getAllHistory();
 List<MedicalHistoryResponseDTO> getPatientHistory(Long patientId);
 List<MedicalHistoryResponseDTO> getDoctorHistory(Long doctorId);
 List<MedicalHistoryResponseDTO> getHistoryByCondition(String conditionName);
 MedicalHistoryResponseDTO updateHistory(Long id, MedicalHistoryRequestDTO request);
 void deleteHistory(Long id);
}
