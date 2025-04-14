package com.healthcare.appointmentservice.dtos;

import com.healthcare.appointmentservice.models.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyData {
    private PatientDTO patient;
    private Appointment appointment;
    private DoctorDTO doctor;
}