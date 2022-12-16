package com.sehati.service.interfaces;

import com.sehati.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> list() throws Exception;

    Patient getById(Integer id) throws Exception;

    Patient create(Patient patient) throws Exception;

    Patient update(Patient patient, Integer id) throws Exception;

    void delete(Integer id) throws Exception;
}
