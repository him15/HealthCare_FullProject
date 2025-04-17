package com.healthcare.appointmentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentBookingRequest {
    private String patientCin;
    private String doctorCin;
    private Date date;
    private String reason;

    public String getPatientCin() {
        return patientCin;
    }

    public void setPatientCin(String patientCin) {
        this.patientCin = patientCin;
    }

    public String getDoctorCin() {
        return doctorCin;
    }

    public void setDoctorCin(String doctorCin) {
        this.doctorCin = doctorCin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}