package com.healthcare.doctorservice.services;

import com.healthcare.doctorservice.models.Doctor;
import com.healthcare.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor findByCin(String cin) {
        return doctorRepository.findByCin(cin);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public String delete(String cin) {
        Doctor doctor = doctorRepository.findByCin(cin);
        if (doctor != null) {
            doctorRepository.delete(doctor);
            return cin;
        }
        return null;
    }

    public Doctor update(Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findByCin(doctor.getCin());
        if (existingDoctor != null) {
            existingDoctor.setCin(doctor.getCin());
            existingDoctor.setFullName(doctor.getFullName());
            existingDoctor.setGender(doctor.getGender());
            existingDoctor.setMobile(doctor.getMobile());
            existingDoctor.setSpecialty(doctor.getSpecialty());
            return doctorRepository.save(existingDoctor);
        }
        return null;
    }

    public List<String> findAllFullname() {
        return doctorRepository.findAllFullNames();
    }

    public Doctor findByFullname(String name) {
        return doctorRepository.findByFullName(name);
    }
}