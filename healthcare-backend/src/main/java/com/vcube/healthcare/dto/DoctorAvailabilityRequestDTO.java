package com.vcube.healthcare.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Data;

@Data
public class DoctorAvailabilityRequestDTO {

    private Long doctorId;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean available;
}
