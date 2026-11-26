package com.vcube.healthcare.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Data;

@Data
public class DoctorAvailabilityResponseDTO {

    private Long availabilityId;

    private Long doctorId;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean available;
}
