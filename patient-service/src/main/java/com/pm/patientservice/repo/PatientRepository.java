package com.pm.patientservice.repo;

import com.pm.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Arpan Mukhopadhyay
 * @title: PatientRepository
 * @projectName patient-service
 * @description: Patient Repository Interface
 * @date 2024-06-10 12:05
 */
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    /**
     *
     * @param email
     * @return
     */
    boolean existsByEmail(String email);
}
