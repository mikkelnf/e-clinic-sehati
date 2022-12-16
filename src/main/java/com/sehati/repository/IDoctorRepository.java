package com.sehati.repository;

import com.sehati.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Integer> {
}
