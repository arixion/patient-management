package com.pm.patientservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

/**
 * @author Arpan Mukhopadhyay
 * @title: Patient
 * @projectName patient-service
 * @description: Patient Model Class
 * @date 2024-06-10 12:00
 */
@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    @DateTimeFormat
    private Date dateOfBirth;

    @Column(name = "registration_date", nullable = false)
    @DateTimeFormat
    private Date dateOfRegistration;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "gender", nullable = false)
    private Gender gender;
}
