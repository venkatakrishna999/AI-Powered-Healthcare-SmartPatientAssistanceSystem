package com.vcube.healthcare.dto;
import java.time.LocalDateTime;
import lombok.Data;
@Data
public class AppointmentRequestDTO {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDateTime;
    private String reason;
    private String status;
    private String notes;
}
