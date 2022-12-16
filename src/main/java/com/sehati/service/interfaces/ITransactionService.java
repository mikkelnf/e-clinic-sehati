package com.sehati.service.interfaces;

import com.sehati.model.DailyIncomeReport;
import com.sehati.model.Transaction;
import com.sehati.model.request.TransactionRequest;

import java.util.List;

public interface ITransactionService {

    List<Transaction> getAllWithPagination(Integer page, Integer size) throws Exception;

    List<Object> getDailyIncomeReport() throws Exception;

    List<Transaction> getByPatientId(Integer id) throws Exception;

    Transaction create(TransactionRequest transactionRequest) throws Exception;
}
