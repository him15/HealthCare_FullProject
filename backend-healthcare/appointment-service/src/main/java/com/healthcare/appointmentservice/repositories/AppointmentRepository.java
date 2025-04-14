package com.healthcare.appointmentservice.repositories;

import com.healthcare.appointmentservice.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findById(Long id);

    List<Appointment> findByPatientCin(String patientCin);
}