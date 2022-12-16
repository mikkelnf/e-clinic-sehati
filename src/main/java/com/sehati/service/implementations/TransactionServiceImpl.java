package com.sehati.service.implementations;

import com.sehati.exception.EntityExistException;
import com.sehati.exception.NotFoundException;
import com.sehati.model.*;
import com.sehati.model.request.TransactionRequest;
import com.sehati.repository.IMedicineSupplierRepository;
import com.sehati.repository.ITransactionRepository;
import com.sehati.repository.IVisitRepository;
import com.sehati.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements ITransactionService {
    private ITransactionRepository transactionRepository;
    private IVisitRepository visitRepository;
    private IMedicineSupplierRepository medicineSupplierRepository;

    public TransactionServiceImpl(@Autowired ITransactionRepository transactionRepository,
                                  @Autowired IVisitRepository visitRepository,
                                  @Autowired IMedicineSupplierRepository medicineSupplierRepository) {
        this.transactionRepository = transactionRepository;
        this.visitRepository = visitRepository;
        this.medicineSupplierRepository = medicineSupplierRepository;
    }

    public List<Transaction> getAllWithPagination(Integer page, Integer size) throws Exception {
        int offset = (page-1)*(size);
        return transactionRepository.getAllWithPagination(size, offset);
    }

    @Override
    public List<Transaction> getByPatientId(Integer id) throws Exception {
        return transactionRepository.getByPatientId(id);
    }

    @Override
    public Transaction create(TransactionRequest transactionRequest) throws Exception {
        Optional<Visit> visitOptional = visitRepository.findById(transactionRequest.getVisitId());
        Optional<MedicineSupplier> medicineSupplierOptional = medicineSupplierRepository.findById(transactionRequest.getMedicineSupplierId());

        if(visitOptional.isEmpty()){
            throw new NotFoundException("Visit not found");
        }else if(medicineSupplierOptional.isEmpty()){
            throw new NotFoundException("Medicine not found");
        }

        try {
            Visit existingVisit = visitOptional.get();
            MedicineSupplier existingMedicineSupplier = medicineSupplierOptional.get();

            Transaction newTransaction = new Transaction();
            newTransaction.setQty(transactionRequest.getQty());
            newTransaction.setNote(transactionRequest.getNote());
            newTransaction.setMedicineSupplier(existingMedicineSupplier);
            newTransaction.setVisit(existingVisit);

            transactionRepository.save(newTransaction);

            return newTransaction;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public List<Object> getDailyIncomeReport() throws Exception {
        return transactionRepository.getDailyIncomeReport();
    }
}
