package com.sehati.repository;

import com.sehati.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Integer> {
}
