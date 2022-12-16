package com.sehati.controller;

import com.sehati.model.Doctor;
import com.sehati.model.response.ErrorResponse;
import com.sehati.model.response.SuccessResponse;
import com.sehati.service.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doctors")
@Validated
public class DoctorController {
    @Autowired
    private IDoctorService doctorService;

    @GetMapping
    public ResponseEntity getAllDoctors(){
        try{
            List<Doctor> doctorList = doctorService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all doctors", doctorList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createDoctor(@RequestBody @Valid Doctor doctor){
        try {
            Doctor newDoctor = doctorService.create(doctor);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success create doctor", newDoctor));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody @Valid Doctor doctor) throws Exception{
        try {
            Doctor updatedDoctor = doctorService.update(doctor, id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update doctor", updatedDoctor));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception{
        doctorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success deleted doctor", null));
    }
}
