package com.healthcare.doctorservice.repository;

import com.healthcare.doctorservice.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByCin(String cin);

    @Query("SELECT d.fullName FROM Doctor d")
    List<String> findAllFullNames();

    Doctor findByFullName(String fullName);
}