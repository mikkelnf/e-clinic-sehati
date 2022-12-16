package com.sehati.service.implementations;

import com.sehati.exception.EntityExistException;
import com.sehati.exception.NotFoundException;
import com.sehati.model.Doctor;
import com.sehati.model.Patient;
import com.sehati.model.Visit;
import com.sehati.model.request.VisitRequest;
import com.sehati.repository.IDoctorRepository;
import com.sehati.repository.IPatientRepository;
import com.sehati.repository.IVisitRepository;
import com.sehati.service.interfaces.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements IVisitService {
    private IVisitRepository visitRepository;
    private IDoctorRepository doctorRepository;
    private IPatientRepository patientRepository;

    public VisitServiceImpl(@Autowired IVisitRepository visitRepository,
                            @Autowired IDoctorRepository doctorRepository,
                            @Autowired IPatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Visit> list() throws Exception {
        return visitRepository.findAll();
    }

    @Override
    public List<Visit> getByPatientId(Integer id) throws Exception {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isEmpty()){
            throw new NotFoundException("Patient not found");
        }

        List<Visit> visitList = list().stream().filter(x-> x.getPatient().getPatientId() == id).collect(Collectors.toList());
        return visitList;
    }

    @Override
    public Visit create(VisitRequest visitRequest) throws Exception {
        Optional<Doctor> doctorOptional = doctorRepository.findById(visitRequest.getDoctorId());
        Optional<Patient> patientOptional = patientRepository.findById(visitRequest.getPatientId());

        if(patientOptional.isEmpty()){
            throw new NotFoundException("Patient not found");
        }if(doctorOptional.isEmpty()){
            throw new NotFoundException("Doctor not found");
        }

        try {
            Doctor existingDoctor = doctorOptional.get();
            Patient existingPatient = patientOptional.get();

            Visit newVisit = new Visit();
            newVisit.setComplaints(visitRequest.getComplaints());
            newVisit.setVisitDate(visitRequest.getVisitDate());
            newVisit.setNote(visitRequest.getNote());
            newVisit.setDoctor(existingDoctor);
            newVisit.setPatient(existingPatient);

            visitRepository.save(newVisit);

            return newVisit;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public Visit update(VisitRequest visitRequest, Integer id) throws Exception {
        Optional<Visit> visitOptional = visitRepository.findById(id);

        if(visitOptional.isEmpty()){
            throw new NotFoundException("Visit not found");
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(visitRequest.getDoctorId());
        Optional<Patient> patientOptional = patientRepository.findById(visitRequest.getPatientId());

        if(patientOptional.isEmpty()){
            throw new NotFoundException("Patient not found");
        }else if(doctorOptional.isEmpty()){
            throw new NotFoundException("Doctor not found");
        }

        try {
            Doctor existingDoctor = doctorOptional.get();
            Patient existingPatient = patientOptional.get();

            Visit newVisit = new Visit();
            newVisit.setVisitId(id);
            newVisit.setComplaints(visitRequest.getComplaints());
            newVisit.setVisitDate(visitRequest.getVisitDate());
            newVisit.setNote(visitRequest.getNote());
            newVisit.setDoctor(existingDoctor);
            newVisit.setPatient(existingPatient);

            visitRepository.save(newVisit);

            return newVisit;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }
}
