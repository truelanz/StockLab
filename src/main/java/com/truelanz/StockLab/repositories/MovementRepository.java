package com.truelanz.StockLab.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.truelanz.StockLab.entities.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT m FROM Movement m JOIN FETCH m.product")
    Page<Movement> findAllWithProduct(Pageable pageable);
    
}
