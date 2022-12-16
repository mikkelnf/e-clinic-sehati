package com.sehati.controller;

import com.sehati.model.Transaction;
import com.sehati.model.request.TransactionRequest;
import com.sehati.model.response.ErrorResponse;
import com.sehati.model.response.SuccessResponse;
import com.sehati.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Validated
public class TransactionController {
    @Autowired
    ITransactionService transactionService;

    @GetMapping(params = {"page", "pageSize"})
    public ResponseEntity getAllTransactions(@RequestParam Integer page, @RequestParam Integer pageSize) throws Exception {
        try {
            List<Transaction> transactionList = transactionService.getAllWithPagination(page, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all data", transactionList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createTransaction(@RequestBody @Valid TransactionRequest transactionRequest){
        try {
            Transaction newTransaction = transactionService.create(transactionRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success create transaction", newTransaction));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity getByPatientId(@PathVariable("id") Integer id){
        try{
            List<Transaction> transactionList = transactionService.getByPatientId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all transactions from patient id : " + id, transactionList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @GetMapping("/daily-income-report")
    public ResponseEntity getDailyIncomeReport(){
        try{
            List<Object> transactionList = transactionService.getDailyIncomeReport();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get report", transactionList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }
}
