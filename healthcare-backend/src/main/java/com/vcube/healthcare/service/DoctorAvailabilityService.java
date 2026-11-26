package com.vcube.healthcare.service;

import java.time.DayOfWeek;
import java.util.List;

import com.vcube.healthcare.entity.DoctorAvailability;

public interface DoctorAvailabilityService {

    DoctorAvailability addAvailability(DoctorAvailability availability);

    DoctorAvailability getAvailabilityById(Long availabilityId);

    List<DoctorAvailability> getAllAvailability();

    List<DoctorAvailability> getDoctorAvailability(Long doctorId);

    List<DoctorAvailability> getAvailabilityByDay(DayOfWeek dayOfWeek);

    List<DoctorAvailability> getDoctorAvailabilityByDay(
            Long doctorId, DayOfWeek dayOfWeek);

    List<DoctorAvailability> getAvailableSchedules();

    DoctorAvailability updateAvailability(
            Long availabilityId, DoctorAvailability availability);

    void deleteAvailability(Long availabilityId);
}