package com.healthcare.patientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.healthcare.patientservice.feignclients")
@SpringBootApplication
public class PatientserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatientserviceApplication.class, args);
    }
}
