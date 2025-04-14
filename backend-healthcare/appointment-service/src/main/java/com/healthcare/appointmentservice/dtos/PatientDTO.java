package com.healthcare.appointmentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}