package com.sehati.service.implementations;

import com.sehati.exception.EntityExistException;
import com.sehati.exception.NotFoundException;
import com.sehati.model.Doctor;
import com.sehati.repository.IDoctorRepository;
import com.sehati.service.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements IDoctorService {
    private IDoctorRepository doctorRepository;

    public DoctorServiceImpl(@Autowired IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> list() throws Exception {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getById(Integer id) throws Exception {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isEmpty()){
            throw new NotFoundException("Doctor not found");
        }
        return doctorOptional.get();
    }

    @Override
    public Doctor create(Doctor doctor) throws Exception {
        try {
            Doctor newDoctor = doctorRepository.save(doctor);
            return newDoctor;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public Doctor update(Doctor doctor, Integer id) throws Exception {
        Optional<Doctor> vendorProductPrice = doctorRepository.findById(id);
        if(vendorProductPrice.isEmpty()){
            throw new EntityExistException("Doctor doesnt exist!");
        }
        try {
            Doctor existingDoctor = getById(id);
            existingDoctor.setDoctorName(doctor.getDoctorName());
            existingDoctor.setDoctorPhone(doctor.getDoctorPhone());
            existingDoctor.setActive(doctor.getActive());

            return doctorRepository.save(existingDoctor);
        }catch (NotFoundException e){
            throw new NotFoundException("Update Failed because ID is not found");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            Doctor existingDoctor = getById(id);
            doctorRepository.delete(existingDoctor);
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
