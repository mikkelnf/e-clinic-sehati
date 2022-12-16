package com.sehati.controller;

import com.sehati.model.MedicineSupplier;
import com.sehati.model.request.MedicinePriceRequest;
import com.sehati.model.request.MedicineSupplierRequest;
import com.sehati.model.response.ErrorResponse;
import com.sehati.model.response.SuccessResponse;
import com.sehati.service.interfaces.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medicines")
@Validated
public class MedicineController {
    @Autowired
    private IMedicineService medicineService;

    @GetMapping
    public ResponseEntity getAllMedicines(){
        try{
            List<MedicineSupplier> medicineList = medicineService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all medicines", medicineList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createMedicine(@RequestBody @Valid MedicineSupplierRequest medicineSupplierRequest){
        try {
            MedicineSupplier newMedicineSupplier = medicineService.create(medicineSupplierRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success create medicine", newMedicineSupplier));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody @Valid MedicinePriceRequest medicinePriceRequest) throws Exception{
        try {
            MedicineSupplier updatedMedicineSupplier = medicineService.update(medicinePriceRequest, id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update doctor", updatedMedicineSupplier));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }
}
