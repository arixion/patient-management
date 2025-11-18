package com.pm.patientservice.exception;

/**
 * @author Arpan Mukhopadhyay
 * @title: EmailAlreadyRegisteredException
 * @projectName patient-service
 * @description: Custom Exception for already registered email
 * @date 2024-06-10 12:30
 */
public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
