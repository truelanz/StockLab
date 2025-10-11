package com.truelanz.StockLab.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.StockLab.entities.Category;
import com.truelanz.StockLab.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCategory(Category category);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
