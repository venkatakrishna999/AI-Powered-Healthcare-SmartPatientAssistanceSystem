package com.vcube.healthcare.service;
import java.util.List;
import com.vcube.healthcare.dto.AppointmentRequestDTO;
import com.vcube.healthcare.dto.AppointmentResponseDTO;
public interface AppointmentService {
 AppointmentResponseDTO bookAppointment(AppointmentRequestDTO request);
 AppointmentResponseDTO getAppointmentById(Long id);
 List<AppointmentResponseDTO> getAllAppointments();
 List<AppointmentResponseDTO> getPatientAppointments(Long patientId);
 List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId);
 List<AppointmentResponseDTO> getAppointmentsByStatus(String status);
 AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO request);
 void deleteAppointment(Long id);
}
