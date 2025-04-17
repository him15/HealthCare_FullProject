package com.healthcare.patientservice.services;

import com.healthcare.patientservice.models.Patient;
import com.healthcare.patientservice.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);  // 200 OK
    }

    public ResponseEntity<Patient> findByCin(String cin) {
        Patient patient = patientRepository.findByCin(cin);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);  // 200 OK
    }

    public ResponseEntity<Patient> save(Patient patient) {
        try {
            Patient existingPatient = patientRepository.findByCin(patient.getCin());
            if (existingPatient == null) {
                Patient savedPatient = patientRepository.save(patient);
                return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);  // 201 Created
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);  // 409 Conflict
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
        }
    }

    public ResponseEntity<String> delete(String cin) {
        Patient patient = patientRepository.findByCin(cin);
        if (patient != null) {
            patientRepository.delete(patient);
            return new ResponseEntity<>(cin, HttpStatus.OK);  // 200 OK
        }
        return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);  // 404 Not Found
    }

    public ResponseEntity<Patient> update(Patient updatedPatient) {
        Patient existingPatient = patientRepository.findByCin(updatedPatient.getCin());
        if (existingPatient != null) {
            existingPatient.setFullName(updatedPatient.getFullName());
            existingPatient.setMobile(updatedPatient.getMobile());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setGender(updatedPatient.getGender());

            Patient savedPatient = patientRepository.save(existingPatient);
            return new ResponseEntity<>(savedPatient, HttpStatus.OK);  // 200 OK
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
    }
}