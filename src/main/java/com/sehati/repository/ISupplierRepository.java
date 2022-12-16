package com.sehati.repository;

import com.sehati.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplierRepository extends JpaRepository<Supplier, Integer> {
}
