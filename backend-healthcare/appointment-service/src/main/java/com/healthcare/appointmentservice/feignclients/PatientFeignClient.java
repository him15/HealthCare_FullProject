package com.healthcare.appointmentservice.feignclients;

import com.healthcare.appointmentservice.dtos.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PATIENT-SERVICE") // , url = "http://patient-service:8083"
public interface PatientFeignClient {

    @PostMapping("/patient/add")
    PatientDTO save(@RequestBody PatientDTO patient);

    @GetMapping("/patient/byCIN/{cin}")
    PatientDTO getByCin(@PathVariable String cin);

    @PutMapping("/patient/update")
    PatientDTO update(@RequestBody PatientDTO patient);
}