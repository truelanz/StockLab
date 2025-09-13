package com.truelanz.StockLab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.StockLab.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
