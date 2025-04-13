package com.healthcare.doctorservice.controllers;

import com.healthcare.doctorservice.models.Doctor;
import com.healthcare.doctorservice.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor save(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/getByCin/{cin}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor findByCin(@PathVariable String cin) {
        return doctorService.findByCin(cin);
    }

    @GetMapping("/findAllByName")
    @ResponseStatus(HttpStatus.OK)
    public List<String> findAllByName() {
        return doctorService.findAllFullname();
    }

    @GetMapping("/findByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor findByName(@PathVariable String name) {
        return doctorService.findByFullname(name);
    }

    @DeleteMapping("/delete/{cin}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String cin) {
        return doctorService.delete(cin);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Doctor update(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }
}