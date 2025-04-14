package com.healthcare.appointmentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {

    private Long appointmentId;
    private Date date;
    private String reason;
    private PatientDTO patient;
    private String doctor;
}