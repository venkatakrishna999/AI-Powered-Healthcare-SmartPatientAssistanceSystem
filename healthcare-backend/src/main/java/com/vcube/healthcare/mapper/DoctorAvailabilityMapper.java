package com.vcube.healthcare.mapper;

import org.springframework.stereotype.Component;

import com.vcube.healthcare.dto.DoctorAvailabilityRequestDTO;
import com.vcube.healthcare.dto.DoctorAvailabilityResponseDTO;
import com.vcube.healthcare.entity.DoctorAvailability;
import com.vcube.healthcare.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DoctorAvailabilityMapper {

    private final DoctorRepository doctorRepository;

    public DoctorAvailability toEntity(DoctorAvailabilityRequestDTO dto) {

        DoctorAvailability availability = new DoctorAvailability();

        availability.setDoctor(
            doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"))
        );

        availability.setDayOfWeek(dto.getDayOfWeek());
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        availability.setAvailable(dto.isAvailable());

        return availability;
    }

    public DoctorAvailabilityResponseDTO toResponseDTO(
            DoctorAvailability availability) {

        DoctorAvailabilityResponseDTO dto =
                new DoctorAvailabilityResponseDTO();

        dto.setAvailabilityId(availability.getAvailabilityId());
        dto.setDoctorId(availability.getDoctor().getDoctorId());
        dto.setDayOfWeek(availability.getDayOfWeek());
        dto.setStartTime(availability.getStartTime());
        dto.setEndTime(availability.getEndTime());
        dto.setAvailable(availability.isAvailable());

        return dto;
    }
}
