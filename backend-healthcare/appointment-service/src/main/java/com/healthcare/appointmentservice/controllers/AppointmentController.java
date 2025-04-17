package com.healthcare.appointmentservice.controllers;

import com.healthcare.appointmentservice.dtos.AppointmentBookingRequest;
import com.healthcare.appointmentservice.dtos.RequestBodyData;
import com.healthcare.appointmentservice.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody AppointmentBookingRequest data) {
        return appointmentService.appointment(data);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return appointmentService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return appointmentService.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RequestBodyData data) {
        return appointmentService.update(data);
    }

    @GetMapping("/byPatient/{cin}")
    public ResponseEntity<?> findAllByPatient(@PathVariable String cin) {
        return appointmentService.findAllByPatientCin(cin);
    }
}