package com.sehati.controller;

import com.sehati.model.Supplier;
import com.sehati.model.response.ErrorResponse;
import com.sehati.model.response.SuccessResponse;
import com.sehati.service.interfaces.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
@Validated
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping
    public ResponseEntity getAllSuppliers(){
        try{
            List<Supplier> supplierList = supplierService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all suppliers", supplierList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createSupplier(@RequestBody @Valid Supplier supplier){
        try {
            Supplier newSupplier = supplierService.create(supplier);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success create supplier", newSupplier));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody @Valid Supplier supplier) throws Exception{
        try {
            Supplier updatedSupplier = supplierService.update(supplier, id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update supplier", updatedSupplier));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws Exception{
        supplierService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success deleted supplier", null));
    }
}
