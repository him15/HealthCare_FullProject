package com.healthcare.appointmentservice.services;

import com.healthcare.appointmentservice.models.Appointment;
import com.healthcare.appointmentservice.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Long delete(Long id) {
        Appointment appointment = appointmentRepository.findById(id);
        appointmentRepository.delete(appointment);
        return id;
    }

    // Update an existing appointment
    public Appointment update(Appointment appointment) {
        Appointment existingAppointment = findById(appointment.getId());
        if (existingAppointment != null) {
            existingAppointment.setReason(appointment.getReason()); // Change to 'reason' instead of 'cause'
            existingAppointment.setDate(appointment.getDate());
            existingAppointment.setDoctorName(appointment.getDoctorName()); // Change to 'doctorName'
            existingAppointment.setPatientCin(appointment.getPatientCin());
            return appointmentRepository.save(existingAppointment);
        } else {
            return null; // or throw exception if preferred
        }
    }


    public List<Appointment> findAllByPatientCin(String patientCin) {
        return appointmentRepository.findByPatientCin(patientCin);
    }
}