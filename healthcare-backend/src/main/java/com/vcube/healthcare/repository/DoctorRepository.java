package com.vcube.healthcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.healthcare.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByUser_UserId(Long userId);

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByAvailableTrue();

    boolean existsByUser_UserId(Long userId);
}