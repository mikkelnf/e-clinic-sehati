package com.sehati.controller;

import com.sehati.model.Visit;
import com.sehati.model.request.VisitRequest;
import com.sehati.model.response.ErrorResponse;
import com.sehati.model.response.SuccessResponse;
import com.sehati.service.interfaces.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/visits")
@Validated
public class VisitController {
    @Autowired
    private IVisitService visitService;

    @GetMapping
    public ResponseEntity getAllVisits(){
        try{
            List<Visit> visitList = visitService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all visits", visitList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity getByPatientId(@PathVariable("id") Integer id){
        try{
            List<Visit> visitList = visitService.getByPatientId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all visits from patient id : " + id, visitList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createVisit(@RequestBody @Valid VisitRequest visitRequest){
        try {
            Visit newVisit = visitService.create(visitRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success create visit", newVisit));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody @Valid VisitRequest visitRequest) throws Exception{
        try {
            Visit updatedVisit = visitService.update(visitRequest, id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update visit", updatedVisit));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }
}
