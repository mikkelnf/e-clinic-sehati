package com.sehati.service.implementations;

import com.sehati.exception.EntityExistException;
import com.sehati.exception.NotFoundException;
import com.sehati.model.Patient;
import com.sehati.repository.IPatientRepository;
import com.sehati.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements IPatientService {
    private IPatientRepository patientRepository;

    public PatientServiceImpl(@Autowired IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public List<Patient> list() throws Exception {
        return patientRepository.findAll();
    }

    @Override
    public Patient getById(Integer id) throws Exception {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(patientOptional.isEmpty()){
            throw new NotFoundException("Patient not found");
        }
        return patientOptional.get();
    }

    @Override
    public Patient create(Patient patient) throws Exception {
        try {
            Patient newPatient = patientRepository.save(patient);
            return newPatient;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public Patient update(Patient patient, Integer id) throws Exception {
        Optional<Patient> vendorProductPrice = patientRepository.findById(id);
        if(vendorProductPrice.isEmpty()){
            throw new EntityExistException("Patient doesnt exist!");
        }
        try {
            Patient existingPatient = getById(id);
            existingPatient.setPatientName(patient.getPatientName());
            existingPatient.setPatientPhone(patient.getPatientPhone());

            return patientRepository.save(existingPatient);
        }catch (NotFoundException e){
            throw new NotFoundException("Update Failed because ID is not found");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            Patient existingPatient = getById(id);
            patientRepository.delete(existingPatient);
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
