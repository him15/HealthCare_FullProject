package com.healthcare.patientservice.services;

import com.healthcare.patientservice.models.Patient;
import com.healthcare.patientservice.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findByCin(String cin) {
        return patientRepository.findByCin(cin);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public String delete(String cin) {
        Patient patient = patientRepository.findByCin(cin);
        if (patient != null) {
            patientRepository.delete(patient);
            return cin;
        }
        return "Patient not found";
    }

    public Patient update(Patient updatedPatient) {
        Patient existingPatient = patientRepository.findByCin(updatedPatient.getCin());
        if (existingPatient != null) {
            existingPatient.setCin(updatedPatient.getCin());
            existingPatient.setFullName(updatedPatient.getFullName());
            existingPatient.setMobile(updatedPatient.getMobile());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setGender(updatedPatient.getGender());

            return patientRepository.save(existingPatient);
        }
        return null;
    }
}