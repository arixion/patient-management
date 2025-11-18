package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arpan Mukhopadhyay
 */
@Getter
@Setter
public class PatientUpdateRequestDTO {

    @Size(max = 120, message = "Name can have at most 120 characters")
    private String name;

    @Size(max = 255, message = "Address can have at most 250 characters")
    private String address;

    @Email(message = "Email should be valid")
    private String email;

    private String phoneNumber;
    private String dateOfBirth;
}
