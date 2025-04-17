package com.healthcare.patientservice.controllers;

import com.healthcare.patientservice.dtos.AppointmentDTO;
import com.healthcare.patientservice.feignclients.AppointmentFeignClient;
import com.healthcare.patientservice.models.Patient;
import com.healthcare.patientservice.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity<List<Patient>> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/byCIN/{cin}")
    public HttpEntity<Patient> getByCin(@PathVariable String cin) {
        return patientService.findByCin(cin);
    }

    @PostMapping("/add")
    public HttpEntity<Patient> save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @DeleteMapping("/delete/{cin}")
    public HttpEntity<String> delete(@PathVariable String cin) {
        return patientService.delete(cin);
    }

    @PutMapping("/update")
    public HttpEntity<Patient> update(@RequestBody Patient patient) {
        return patientService.update(patient);
    }

    @GetMapping("/appointments/{cin}")
    public ResponseEntity<?> getAppointmentsForPatient(@PathVariable String cin) {
        List<AppointmentDTO> appointments = appointmentFeignClient.findAllByPatient(cin);
        return ResponseEntity.ok(appointments);
    }
}