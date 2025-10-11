package com.truelanz.StockLab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.StockLab.dto.EmployeeDTO;
import com.truelanz.StockLab.entities.Employee;
import com.truelanz.StockLab.repositories.EmployeeRepository;
import com.truelanz.StockLab.repositories.ServiceJobRepository;
import com.truelanz.StockLab.services.exceptions.DatabaseException;
import com.truelanz.StockLab.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ServiceJobRepository serviceRepository;

    /* @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAllPaged(Pageable pageable) {
        Page<Employee> result = repository.findAll(pageable);
        return result.map(x -> new EmployeeDTO(x));
    } */

    //Pesquisa paginada e pesquisa por nome
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> SearchPaged(Pageable pageable, String search) {
        Page<Employee> employees;

        if (search != null && !search.isEmpty()) {
            employees = repository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            employees = repository.findAll(pageable);
        }

        // Convertendo para DTO
        return employees.map(EmployeeDTO::new);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long id) {
        Employee entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee id: %d not found: ", id)));

        return new EmployeeDTO(entity);
    }


    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setName(dto.getName());

        entity = repository.save(entity);
        return new EmployeeDTO(entity);
    }

    @Transactional
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        try {
            Employee entity = repository.getReferenceById(id); // busca sem ir ao banco de imediato
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new EmployeeDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Employee id not found: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        Employee Employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Verifica se há serviços vinculados
        if (serviceRepository.existsByEmployee(Employee)) {
            throw new DatabaseException("Cannot delete Employee with linked products");
        }

        try {
            repository.delete(Employee);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }
}
