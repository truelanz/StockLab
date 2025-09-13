package com.truelanz.StockLab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.StockLab.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
