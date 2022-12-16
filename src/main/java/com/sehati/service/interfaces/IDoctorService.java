package com.sehati.service.interfaces;

import com.sehati.model.Doctor;

import java.util.List;

public interface IDoctorService {
    List<Doctor> list() throws Exception;

    Doctor getById(Integer id) throws Exception;

    Doctor create(Doctor doctor) throws Exception;

    Doctor update(Doctor doctor, Integer id) throws Exception;

    void delete(Integer id) throws Exception;
}
