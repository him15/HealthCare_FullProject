package com.healthcare.doctorservice.controllers;

import com.healthcare.doctorservice.models.Doctor;
import com.healthcare.doctorservice.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/getByCin/{cin}")
    public ResponseEntity<?> findByCin(@PathVariable String cin) {
        return doctorService.findByCin(cin);
    }

    @GetMapping("/findAllByName")
    public ResponseEntity<?> findAllByName() {
        return doctorService.findAllFullname();
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return doctorService.findByFullname(name);
    }

    @DeleteMapping("/delete/{cin}")
    public ResponseEntity<?> delete(@PathVariable String cin) {
        return doctorService.delete(cin);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }
}