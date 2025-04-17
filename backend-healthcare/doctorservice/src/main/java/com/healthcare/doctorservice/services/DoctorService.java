package com.healthcare.doctorservice.services;

import com.healthcare.doctorservice.models.Doctor;
import com.healthcare.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public ResponseEntity<?> save(Doctor doctor) {
        // Check if a doctor with the same CIN already exists
        Doctor existingDoctor = doctorRepository.findByCin(doctor.getCin());
        if (existingDoctor != null) {
            // Return conflict status if the doctor already exists
            return new ResponseEntity<>("Doctor with CIN " + doctor.getCin() + " already exists", HttpStatus.CONFLICT);
        }
        try {
            // Save the new doctor if no duplicate CIN is found
            Doctor saved = doctorRepository.save(doctor);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any other exceptions during the save process
            return new ResponseEntity<>("Failed to save doctor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            return new ResponseEntity<>("No doctors found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    public ResponseEntity<?> findByCin(String cin) {
        Doctor doctor = doctorRepository.findByCin(cin);
        if (doctor == null) {
            return new ResponseEntity<>("Doctor not found with CIN: " + cin, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    public ResponseEntity<?> findAllFullname() {
        List<String> names = doctorRepository.findAllFullNames();
        if (names.isEmpty()) {
            return new ResponseEntity<>("No doctor names found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    public ResponseEntity<?> findByFullname(String name) {
        Doctor doctor = doctorRepository.findByFullName(name);
        if (doctor == null) {
            return new ResponseEntity<>("Doctor not found with name: " + name, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(String cin) {
        Doctor doctor = doctorRepository.findByCin(cin);
        if (doctor != null) {
            doctorRepository.delete(doctor);
            return new ResponseEntity<>("Doctor deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> update(Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findByCin(doctor.getCin());
        if (existingDoctor != null) {
            existingDoctor.setCin(doctor.getCin());
            existingDoctor.setFullName(doctor.getFullName());
            existingDoctor.setGender(doctor.getGender());
            existingDoctor.setMobile(doctor.getMobile());
            existingDoctor.setSpecialty(doctor.getSpecialty());

            Doctor updated = doctorRepository.save(existingDoctor);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>("Doctor not found with CIN: " + doctor.getCin(), HttpStatus.NOT_FOUND);
    }
}