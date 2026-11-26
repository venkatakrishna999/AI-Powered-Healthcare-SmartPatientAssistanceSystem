package com.vcube.healthcare.repository;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.healthcare.entity.DoctorAvailability;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findByDoctor_DoctorId(Long doctorId);

    List<DoctorAvailability> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<DoctorAvailability> findByDoctor_DoctorIdAndDayOfWeek(
            Long doctorId, DayOfWeek dayOfWeek);

    List<DoctorAvailability> findByAvailableTrue();
}