package com.healthcare.appointmentservice.repositories;

import com.healthcare.appointmentservice.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientCin(String patientCin);
}