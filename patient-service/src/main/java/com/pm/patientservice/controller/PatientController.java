package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientUpdateRequestDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@code title:} PatientController
 * {@code projectName} patient-service
 *
 * @author Arpan Mukhopadhyay
 * @description Patient Controller Class
 * @date 2024-06-10 12:10
 */
@RestController
@RequestMapping("/patients")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private @Autowired PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> listPatients() {
        logger.trace("Received request to list patients");
        
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        logger.trace("Received request to create a new patient");
        
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable String id, @Valid @RequestBody PatientUpdateRequestDTO patientRequestDTO) {
        logger.trace("Received request to update patient with id: {}", id);
        
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable String id) {
        logger.trace("Received request to delete patient with id: {}", id);
        
        PatientResponseDTO patientResponseDTO = patientService.deletePatient(id);
        return ResponseEntity.ok(patientResponseDTO);
    }
}
