package com.vcube.healthcare.service;

import java.util.List;import java.util.List;
import com.vcube.healthcare.entity.Patient;



import com.vcube.healthcare.dto.PatientRequestDTO;
import com.vcube.healthcare.dto.PatientResponseDTO;
import com.vcube.healthcare.entity.Patient;

public interface PatientService {

	PatientResponseDTO addPatient(PatientRequestDTO dto);
	
	Patient getPatientById(Long patientId);

	PatientResponseDTO getPatientResponseById(Long patientId);

	Patient getPatientByUserId(Long userId);

	List<PatientResponseDTO> getAllPatients();

	PatientResponseDTO updatePatient(Long patientId, Patient patient);

	void deletePatient(Long patientId);
}