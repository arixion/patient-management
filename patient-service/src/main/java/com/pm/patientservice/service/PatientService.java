package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientUpdateRequestDTO;
import com.pm.patientservice.exception.EmailAlreadyRegisteredException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repo.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Arpan Mukhopadhyay
 * @title: PatientService
 * @projectName patient-service
 * @description: Patient Service Class
 * @date 2024-06-10 12:10
 */
@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    private @Autowired PatientRepository patientRepository;

    /**
     * Retrieves all patients from the database and converts them to PatientResponseDTO
     *
     * @return List of PatientResponseDTO or null if no patients found
     */
    public List<PatientResponseDTO> getPatients() {
        logger.trace("Fetching all patients");
        List<Patient> patients = patientRepository.findAll();
        if (null == patients || patients.isEmpty()) {
            logger.warn("No patients found in the database");
            return List.of();
        }
        logger.info("Found {} patients", patients.size());
        return PatientMapper.toResponseDTOList(patients);
    }

    /**
     * Creates a new patient from PatientRequestDTO
     *
     * @param patientRequestDTO the patient request data
     * @return PatientResponseDTO of the created patient
     */
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        logger.trace("Creating new patient with name: {}", patientRequestDTO.getName());
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            logger.error("Patient with email {} already exists", patientRequestDTO.getEmail());
            throw new EmailAlreadyRegisteredException("A patient with the given email " + patientRequestDTO.getEmail() + " already exists");
        }
        // Convert DTO to entity
        Patient patient = PatientMapper.toEntity(patientRequestDTO);
        // Save to database
        Patient savedPatient = patientRepository.save(patient);
        logger.info("Successfully created patient with ID: {}", savedPatient.getId());
        // Convert entity to response DTO
        return PatientMapper.toResponseDTO(savedPatient);
    }

    /**
     *
     * @param patientId
     * @param patientRequestDTO
     * @return
     */
    public PatientResponseDTO updatePatient(final String patientId, PatientUpdateRequestDTO patientRequestDTO) {
        if (null == patientId || patientId.isEmpty()) {
            logger.error("Invalid patient ID {} provided for update", patientId);
            new PatientNotFoundException("No patient found with the given id " + patientId);
        }

        if (null != patientRequestDTO.getEmail() && patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyRegisteredException("A patient with the given email " + patientRequestDTO.getEmail() + " already exists");
        }

        final UUID uuid = UUID.fromString(patientId);
        Patient patient = patientRepository.findById(uuid).orElseThrow(()-> new PatientNotFoundException("No patient found with the given id " + patientId));
        patient = PatientMapper.updatePatient(patientRequestDTO, patient);
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toResponseDTO(savedPatient);
    }

    public PatientResponseDTO deletePatient(final String patientId) {
        if (null == patientId || patientId.isEmpty()) {
            logger.error("Invalid patient ID {} provided for deletion", patientId);
            throw new PatientNotFoundException("No patient found with the given id " + patientId);
        }

        final UUID uuid = UUID.fromString(patientId);
        Patient patient = patientRepository.findById(uuid).orElseThrow(()-> new PatientNotFoundException("No patient found with the given id " + patientId));

        patientRepository.delete(patient);
        return PatientMapper.toResponseDTO(patient);
    }
}
