/*
 *
 */
package com.pm.patientservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Arpan Mukhopadhyay
 */
@SpringBootApplication
public class PatientServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceApplication.class);

    /**
     * Main method to start the Patient Service Application
     *
     * @param args
     */
    public static void main(String[] args) {
        logger.info("Patient Service Application Starting...");
        SpringApplication.run(PatientServiceApplication.class, args);
    }
}
