package com.healthcare.patientservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String cin;
    private String fullName;
    private String mobile;
    private Integer age;
    private String gender;
    private List<AppointmentDTO> appointmentList;
}