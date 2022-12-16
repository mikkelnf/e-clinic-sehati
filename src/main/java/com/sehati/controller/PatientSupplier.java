package com.sehati.controller;

import com.sehati.model.Patient;
import com.sehati.model.response.ErrorResponse;
import com.sehati.model.response.SuccessResponse;
import com.sehati.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients")
@Validated
public class PatientSupplier {
    @Autowired
    private IPatientService patientService;

    @GetMapping
    public ResponseEntity getAllPatients(){
        try{
            List<Patient> patientList = patientService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all patients", patientList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody @Valid Patient patient){
        try {
            Patient newPatient = patientService.create(patient);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success create patient", newPatient));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody @Valid Patient patient) throws Exception{
        try {
            Patient updatedPatient = patientService.update(patient, id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update patient", updatedPatient));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception{
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success deleted patient", null));
    }
}
