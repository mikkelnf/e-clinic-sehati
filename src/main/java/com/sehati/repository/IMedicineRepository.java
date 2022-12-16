package com.sehati.repository;

import com.sehati.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMedicineRepository extends JpaRepository<Medicine, Integer> {
    @Query(value = "SELECT * FROM m_medicine WHERE medicine_name = ?1", nativeQuery = true)
    Medicine getByName(String name);
}
