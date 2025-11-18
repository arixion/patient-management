package com.pm.patientservice.dto;

import com.pm.patientservice.model.Gender;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arpan Mukhopadhyay
 * @title: PatientResponseDTO
 * @projectName patient-service
 * @description: Patient Response Data Transfer Object
 * @date 2024-06-10 12:15
 */
@Getter
@Setter
public class PatientResponseDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String dateOfRegistration;
    private Gender gender;
}
