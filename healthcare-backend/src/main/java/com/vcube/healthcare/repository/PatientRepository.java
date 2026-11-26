package com.vcube.healthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.healthcare.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByUser_UserId(Long userId);

    boolean existsByUser_UserId(Long userId);

}