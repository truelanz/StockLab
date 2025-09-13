package com.truelanz.StockLab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.StockLab.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
