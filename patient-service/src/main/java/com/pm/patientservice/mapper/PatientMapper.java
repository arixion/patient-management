package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientUpdateRequestDTO;
import com.pm.patientservice.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arpan Mukhopadhyay
 * @title: PatientMapper
 * @projectName patient-service
 * @description: Patient Mapper Class
 * @date 2024-06-10 12:20
 */
public final class PatientMapper {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private PatientMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Converts a Patient entity to PatientResponseDTO
     *
     * @param patient the patient entity to convert
     * @return PatientResponseDTO object
     */
    public static PatientResponseDTO toResponseDTO(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId() != null ? patient.getId().toString() : null);
        dto.setName(patient.getName());
        dto.setAddress(patient.getAddress());
        dto.setEmail(patient.getEmail());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setGender(patient.getGender());

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);

        if (patient.getDateOfBirth() != null) {
            dto.setDateOfBirth(dateFormatter.format(patient.getDateOfBirth()));
        }

        if (patient.getDateOfRegistration() != null) {
            dto.setDateOfRegistration(dateFormatter.format(patient.getDateOfRegistration()));
        }

        return dto;
    }

    /**
     * Converts a list of Patient entities to a list of PatientResponseDTO
     *
     * @param patients the list of patient entities to convert
     * @return List of PatientResponseDTO objects
     */
    public static List<PatientResponseDTO> toResponseDTOList(List<Patient> patients) {
        if (null == patients) {
            return null;
        }

        return patients.stream()
                .map(PatientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts PatientRequestDTO to Patient entity
     *
     * @param requestDTO the request DTO to convert
     * @return Patient entity object
     * @throws IllegalArgumentException if date format is invalid
     */
    public static Patient toEntity(PatientRequestDTO requestDTO) {
        if (null == requestDTO) {
            return null;
        }

        Patient patient = new Patient();
        patient.setName(requestDTO.getName());
        patient.setAddress(requestDTO.getAddress());
        patient.setEmail(requestDTO.getEmail());
        patient.setPhoneNumber(requestDTO.getPhoneNumber());
        patient.setGender(requestDTO.getGender());
        patient.setDateOfRegistration(new Date());

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        if (null != requestDTO.getDateOfBirth()) {
            try {
                patient.setDateOfBirth(dateFormatter.parse(requestDTO.getDateOfBirth()));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format. Expected format: " + DATE_FORMAT, e);
            }
        }

        // Set audit fields
        Date now = new Date();
        patient.setCreatedAt(now);
        patient.setUpdatedAt(now);

        return patient;
    }

    /**
     *
     * @param patientRequestDTO
     * @param patient
     * @return
     */
    public static Patient updatePatient(PatientUpdateRequestDTO patientRequestDTO, Patient patient) {
        if (null == patientRequestDTO || null == patient) {
            return patient;
        }

        if (null != patientRequestDTO.getName()) {
            patient.setName(patientRequestDTO.getName());
        }

        if (null != patientRequestDTO.getEmail()) {
            patient.setEmail(patientRequestDTO.getEmail());
        }

        if (null != patientRequestDTO.getAddress()) {
            patient.setAddress(patientRequestDTO.getAddress());
        }

        if (null != patientRequestDTO.getPhoneNumber()) {
            patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
        }

        if (null != patientRequestDTO.getDateOfBirth()) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            try {
                patient.setDateOfBirth(dateFormatter.parse(patientRequestDTO.getDateOfBirth()));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format. Expected format: " + DATE_FORMAT, e);
            }
        }

        return patient;
    }
}
