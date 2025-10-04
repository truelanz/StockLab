package com.truelanz.StockLab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.truelanz.StockLab.dto.ServiceJobDTO;
import com.truelanz.StockLab.entities.Client;
import com.truelanz.StockLab.entities.Employee;
import com.truelanz.StockLab.entities.Product;
import com.truelanz.StockLab.entities.ServiceJob;
import com.truelanz.StockLab.repositories.ClientRepository;
import com.truelanz.StockLab.repositories.EmployeeRepository;
import com.truelanz.StockLab.repositories.ProductRepository;
import com.truelanz.StockLab.repositories.ServiceJobRepository;
import com.truelanz.StockLab.services.exceptions.DatabaseException;
import com.truelanz.StockLab.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceJobService {

    @Autowired
    private ServiceJobRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ServiceJobDTO> findAllPaged(Pageable pageable) {
        Page<ServiceJob> result = repository.findAll(pageable);
        return result.map(x -> new ServiceJobDTO(x));
    }

    @Transactional
    public ServiceJobDTO insert(ServiceJobDTO dto) {
        ServiceJob entity = new ServiceJob();
        copyDtoToEntity(dto, entity);

        repository.save(entity);
        return new ServiceJobDTO(entity);
    }

    @Transactional
    public ServiceJobDTO update(Long id, ServiceJobDTO dto) {
        try {
            ServiceJob entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ServiceJobDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ServiceJob not found, id " + id);
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            while (cause != null) {
                System.out.println("CAUSA: " + cause.getMessage());
                cause = cause.getCause();
            }
            throw new DatabaseException("Erro ao atualizar ServiceJob: " + e.getClass().getName());
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
         if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

    //Mepeia dtos para entidades
    private void copyDtoToEntity(ServiceJobDTO dto, ServiceJob entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setInitTime(dto.getInitTime());
        entity.setFinalTime(dto.getFinalTime());
        entity.setInitDate(dto.getInitDate());
        entity.setFinalDate(dto.getFinalDate());

        // Muitos-para-um: Employee
        if (dto.getEmployee() != null && dto.getEmployee().getId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Employee not found, id " + dto.getEmployee().getId()));
            entity.setEmployee(employee);
        }

        // Muitos-para-um: Client
        if (dto.getClient() != null && dto.getClient().getId() != null) {
            Client client = clientRepository.findById(dto.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Client not found, id " + dto.getClient().getId()));
            entity.setClient(client);
        }

        // Muitos-para-muitos: Products
        if (dto.getProducts() != null) {
            entity.getProducts().clear();
            dto.getProducts().forEach(prodMinDto -> {
                Product product = productRepository.getReferenceById(prodMinDto.getId());
                entity.getProducts().add(product);
            });
        }

    }
}