package com.sehati.repository;

import com.sehati.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM transaction t ORDER BY t.visit_id LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Transaction> getAllWithPagination(int pageSize, int offset);

    @Query(value = "SELECT t.* FROM transaction t JOIN visit ON visit.visit_id = t.visit_id WHERE visit.patient_id = ?1", nativeQuery = true)
    List<Transaction> getByPatientId(Integer id);

    @Query(value = "SELECT v.visit_date, " +
            "SUM(t.qty) AS qty_sold_medicine, " +
            "SUM(ms.medicine_price * t.qty) AS total_sold_medicine " +
            "FROM transaction t " +
            "JOIN visit v ON v.visit_id = t.visit_id " +
            "JOIN m_patient p ON p.patient_id = v.patient_id " +
            "JOIN medicine_supplier ms ON ms.medicine_supplier_id = t.medicine_supplier_id " +
            "JOIN m_medicine m ON m.medicine_id = ms.medicine_id " +
            "GROUP BY 1", nativeQuery = true)
    List<Object> getDailyIncomeReport();
}
