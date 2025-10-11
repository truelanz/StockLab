package com.truelanz.StockLab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.truelanz.StockLab.entities.Employee;
import com.truelanz.StockLab.entities.ServiceJob;

public interface ServiceJobRepository extends JpaRepository<ServiceJob, Long>, JpaSpecificationExecutor<ServiceJob> {

    boolean existsByEmployee(Employee category);
}
