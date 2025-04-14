package com.healthcare.appointmentservice.feignclients;

import com.healthcare.appointmentservice.dtos.DoctorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DOCTOR-SERVICE", url = "http://localhost:8082")
public interface DoctorFeignClient {

    @GetMapping("/doctor/findByName/{name}")
    DoctorDTO findByName(@PathVariable String name);
}