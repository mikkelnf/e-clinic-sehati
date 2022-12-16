package com.sehati.repository;

import com.sehati.model.MedicineSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicineSupplierRepository extends JpaRepository<MedicineSupplier, Integer> {
}
