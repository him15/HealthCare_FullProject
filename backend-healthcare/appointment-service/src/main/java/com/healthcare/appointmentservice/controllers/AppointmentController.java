package com.healthcare.appointmentservice.controllers;

import com.healthcare.appointmentservice.dtos.DoctorDTO;
import com.healthcare.appointmentservice.dtos.PatientDTO;
import com.healthcare.appointmentservice.dtos.AppointmentDTO;
import com.healthcare.appointmentservice.dtos.RequestBodyData;
import com.healthcare.appointmentservice.feignclients.DoctorFeignClient;
import com.healthcare.appointmentservice.feignclients.PatientFeignClient;
import com.healthcare.appointmentservice.models.Appointment;
import com.healthcare.appointmentservice.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorFeignClient doctorFeignClient;

    @Autowired
    private PatientFeignClient patientFeignClient;

    @PostMapping("/add")
    public Appointment save(@RequestBody RequestBodyData data) {
        try {
            PatientDTO patientDTO = data.getPatient();
            Appointment appointment = data.getAppointment();
            DoctorDTO doctorDTO = data.getDoctor();

            PatientDTO savedPatient = patientFeignClient.save(patientDTO);
            if (savedPatient == null) {
                throw new Exception("Error while saving patient");
            }

            DoctorDTO doctorDetails = doctorFeignClient.findByName(doctorDTO.getFullName());
            if (doctorDetails == null) {
                throw new Exception("Doctor not found");
            }

            appointment.setPatientCin(savedPatient.getCin());
            appointment.setDoctorName(doctorDetails.getFullName());

            return appointmentService.save(appointment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create appointment. Cause: " + e.getMessage(), e);
        }
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentDTO findById(@PathVariable Long id) {
        Appointment appointment = appointmentService.findById(id);
        PatientDTO patientDTO = patientFeignClient.getByCin(appointment.getPatientCin());

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(patientDTO);
        appointmentDTO.setAppointmentId(appointment.getAppointmentId());
        appointmentDTO.setDate(appointment.getDate());
        appointmentDTO.setReason(appointment.getReason());
        appointmentDTO.setDoctorName(appointment.getDoctorName());

        return appointmentDTO;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long delete(@PathVariable Long id) {
        return appointmentService.delete(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Appointment update(@RequestBody RequestBodyData data) {
        try {
            PatientDTO patientDTO = data.getPatient();
            Appointment appointment = data.getAppointment();
            DoctorDTO doctorDTO = data.getDoctor();

            PatientDTO updatedPatient = patientFeignClient.update(patientDTO);
            appointment.setPatientCin(updatedPatient.getCin());
            appointment.setDoctorName(doctorDTO.getFullName());

            return appointmentService.update(appointment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update appointment. Cause: " + e.getMessage(), e);
        }
    }

    @GetMapping("/allByPatient/{cin}")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findAllByPatient(@PathVariable String cin) {
        return appointmentService.findAllByPatient(cin);
    }
}