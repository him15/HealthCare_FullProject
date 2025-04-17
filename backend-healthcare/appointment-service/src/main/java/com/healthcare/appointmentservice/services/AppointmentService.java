package com.healthcare.appointmentservice.services;

import com.healthcare.appointmentservice.dtos.*;
import com.healthcare.appointmentservice.feignclients.DoctorFeignClient;
import com.healthcare.appointmentservice.feignclients.PatientFeignClient;
import com.healthcare.appointmentservice.models.Appointment;
import com.healthcare.appointmentservice.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientFeignClient patientFeignClient;

    @Autowired
    private DoctorFeignClient doctorFeignClient;

    public ResponseEntity<?> appointment(AppointmentBookingRequest request) {
        try {
            // 1. Validate patient
            PatientDTO patient = patientFeignClient.getByCin(request.getPatientCin());


            if (patient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Patient not found with CIN: " + request.getPatientCin());
            }

            // 2. Validate doctor
            DoctorDTO doctor = doctorFeignClient.findByCin(request.getDoctorCin());

            if (doctor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Doctor not found with CIN: " + request.getDoctorCin());
            }

            // 3. Create appointment
            Appointment appointment = new Appointment();
            appointment.setDate(request.getDate());
            appointment.setReason(request.getReason());
            appointment.setPatientCin(patient.getCin());
            appointment.setDoctorName(doctor.getFullName());

            Appointment saved = appointmentRepository.save(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while booking appointment: " + e.getMessage());
        }
    }

    public ResponseEntity<?> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        if (appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(appointments);
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<Appointment> optional = appointmentRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
        }

        Appointment appointment = optional.get();
        PatientDTO patient = patientFeignClient.getByCin(appointment.getPatientCin());

        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentId(appointment.getId());
        dto.setDate(appointment.getDate());
        dto.setReason(appointment.getReason());
        dto.setDoctorName(appointment.getDoctorName());
        dto.setPatient(patient);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<Appointment> optional = appointmentRepository.findById(id);
        if (optional.isPresent()) {
            appointmentRepository.delete(optional.get());
            return ResponseEntity.ok("Deleted appointment with ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
    }

    public ResponseEntity<?> update(RequestBodyData data) {
        try {
            Appointment incoming = data.getAppointment();
            Optional<Appointment> optional = appointmentRepository.findById(incoming.getId());

            if (optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
            }

            PatientDTO updatedPatient = patientFeignClient.update(data.getPatient());
            DoctorDTO doctor = data.getDoctor();

            Appointment appointment = optional.get();
            appointment.setReason(incoming.getReason());
            appointment.setDate(incoming.getDate());
            appointment.setDoctorName(doctor.getFullName());
            appointment.setPatientCin(updatedPatient.getCin());

            Appointment updated = appointmentRepository.save(appointment);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating appointment: " + e.getMessage());
        }
    }

    public ResponseEntity<?> findAllByPatientCin(String cin) {
        List<Appointment> appointments = appointmentRepository.findByPatientCin(cin);
        if (appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointments found for CIN: " + cin);
        }
        return ResponseEntity.ok(appointments);
    }
}