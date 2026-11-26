package com.vcube.healthcare.service.impl;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vcube.healthcare.entity.DoctorAvailability;
import com.vcube.healthcare.repository.DoctorAvailabilityRepository;
import com.vcube.healthcare.service.DoctorAvailabilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService {

    private final DoctorAvailabilityRepository availabilityRepository;

    @Override
    public DoctorAvailability addAvailability(DoctorAvailability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public DoctorAvailability getAvailabilityById(Long availabilityId) {
        return availabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
    }

    @Override
    public List<DoctorAvailability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

    @Override
    public List<DoctorAvailability> getDoctorAvailability(Long doctorId) {
        return availabilityRepository.findByDoctor_DoctorId(doctorId);
    }

    @Override
    public List<DoctorAvailability> getAvailabilityByDay(DayOfWeek dayOfWeek) {
        return availabilityRepository.findByDayOfWeek(dayOfWeek);
    }

    @Override
    public List<DoctorAvailability> getDoctorAvailabilityByDay(
            Long doctorId, DayOfWeek dayOfWeek) {

        return availabilityRepository.findByDoctor_DoctorIdAndDayOfWeek(
                doctorId, dayOfWeek);
    }

    @Override
    public List<DoctorAvailability> getAvailableSchedules() {
        return availabilityRepository.findByAvailableTrue();
    }

    @Override
    public DoctorAvailability updateAvailability(
            Long availabilityId, DoctorAvailability availability) {

        DoctorAvailability existing = getAvailabilityById(availabilityId);
        availability.setAvailabilityId(existing.getAvailabilityId());

        return availabilityRepository.save(availability);
    }

    @Override
    public void deleteAvailability(Long availabilityId) {
        availabilityRepository.delete(getAvailabilityById(availabilityId));
    }
}