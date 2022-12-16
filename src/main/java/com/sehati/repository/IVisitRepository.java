package com.sehati.repository;

import com.sehati.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisitRepository extends JpaRepository<Visit, Integer> {
}
