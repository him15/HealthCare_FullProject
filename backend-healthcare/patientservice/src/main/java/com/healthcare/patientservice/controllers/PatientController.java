package com.healthcare.patientservice.controllers;

import com.healthcare.patientservice.dtos.PatientDTO;
import com.healthcare.patientservice.dtos.AppointmentDTO;
import com.healthcare.patientservice.feignclients.AppointmentFeignClient;
import com.healthcare.patientservice.models.Patient;
import com.healthcare.patientservice.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentFeignClient appointmentFeignClient;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/byCIN/{cin}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getByCin(@PathVariable String cin) {
        return patientService.findByCin(cin);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient save(@RequestBody Patient patient) {
        try {
            Patient existingPatient = patientService.findByCin(patient.getCin());
            if (existingPatient == null) {
                return patientService.save(patient);
            } else {
                System.out.println("Patient already exists.");
                return existingPatient;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while saving patient. Cause: " + e.getMessage(), e);
        }
    }

    @DeleteMapping("/delete/{cin}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String cin) {
        return patientService.delete(cin);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Patient update(@RequestBody Patient patient) {
        return patientService.update(patient);
    }
}