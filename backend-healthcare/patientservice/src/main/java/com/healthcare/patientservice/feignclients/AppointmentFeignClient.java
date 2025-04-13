package com.healthcare.patientservice.feignclients;

import com.healthcare.patientservice.dtos.AppointmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "APPOINTMENT-SERVICE", url = "http://localhost:8081")
public interface AppointmentFeignClient {

    @GetMapping("/appointments/getAll")
    List<AppointmentDTO> findAll();

    @GetMapping("/appointments/byPatient/{cin}")
    List<AppointmentDTO> findAllByPatient(@PathVariable String cin);
}