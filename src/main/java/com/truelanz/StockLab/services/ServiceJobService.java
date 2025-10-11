package com.truelanz.StockLab.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.truelanz.StockLab.dto.ServiceJobDTO;
import com.truelanz.StockLab.dto.ServiceJobSearchDTO;
import com.truelanz.StockLab.entities.Client;
import com.truelanz.StockLab.entities.Employee;
import com.truelanz.StockLab.entities.Product;
import com.truelanz.StockLab.entities.ServiceJob;
import com.truelanz.StockLab.entities.ServiceProduct;
import com.truelanz.StockLab.entities.ServiceStatus;
import com.truelanz.StockLab.repositories.ClientRepository;
import com.truelanz.StockLab.repositories.EmployeeRepository;
import com.truelanz.StockLab.repositories.ProductRepository;
import com.truelanz.StockLab.repositories.ServiceJobRepository;
import com.truelanz.StockLab.services.exceptions.DatabaseException;
import com.truelanz.StockLab.services.exceptions.ResourceNotFoundException;
import com.truelanz.StockLab.specifications.ServiceJobSpecification;

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

    //Pesquisa dinâmica
    @Transactional
    public Page<ServiceJobDTO> search(ServiceJobSearchDTO filters, Pageable pageable) {

        Specification<ServiceJob> spec = Specification.allOf(
            ServiceJobSpecification.nameContains(filters.getName()),
            ServiceJobSpecification.dateGreaterOrEqual(filters.getStartDate()),
            ServiceJobSpecification.dateLessOrEqual(filters.getEndDate()),
            ServiceJobSpecification.byClient(filters.getClientId()),
            ServiceJobSpecification.byEmployee(filters.getEmployeeId())
        );

        return repository.findAll(spec, pageable).map(ServiceJobDTO::new);
    }


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

    @Transactional
    public ServiceJobDTO finishService(Long serviceId) {
        ServiceJob service = repository.findById(serviceId)
            .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        if (service.getStatus() == ServiceStatus.FINALIZED) {
            throw new DatabaseException("Service is already finalized");
        }

        // Baixa de estoque e cálculo de total
        for (ServiceProduct sp : service.getProducts()) {
            Product product = sp.getProduct();
            int currentQty = product.getCurrentQuantity() != null ? product.getCurrentQuantity() : 0;
            int newQty = currentQty - (sp.getQuantityUsed() != null ? sp.getQuantityUsed() : 0);

            if (newQty < 0) {
                throw new DatabaseException("Insufficient stock for product: " + product.getName());
            }

            product.setCurrentQuantity(newQty);
            productRepository.save(product);
        }

        BigDecimal productsTotal = service.getProducts().stream()
                .map(ServiceProduct::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal serviceValue = service.getServiceValue() != null ? service.getServiceValue() : BigDecimal.ZERO;
        service.setTotalValue(serviceValue.add(productsTotal));

        // Atualiza status
        service.setStatus(ServiceStatus.FINALIZED);

        repository.save(service);

        // Retorna DTO atualizado
        return new ServiceJobDTO(service);
    }


    //Mepeia dtos para entidades
    private void copyDtoToEntity(ServiceJobDTO dto, ServiceJob entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setInitTime(dto.getInitTime() != null ? dto.getInitTime() : LocalTime.now());
        entity.setFinalTime(dto.getFinalTime() != null ? dto.getFinalTime() : LocalTime.now());
        entity.setInitDate(dto.getInitDate() != null ? dto.getInitDate() : LocalDate.now());
        entity.setFinalDate(dto.getFinalDate() != null ? dto.getFinalDate() : LocalDate.now());
        entity.setServiceValue(dto.getServiceValue());
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

        // Employee
        if (dto.getEmployee() != null && dto.getEmployee().getId() != null) {
            Employee employee = employeeRepository.getReferenceById(dto.getEmployee().getId());
            entity.setEmployee(employee);
        }

        // Client
        if (dto.getClient() != null && dto.getClient().getId() != null) {
            Client client = clientRepository.getReferenceById(dto.getClient().getId());
            entity.setClient(client);
        }

        // Products
        entity.getProducts().clear();
        if (dto.getProducts() != null) {
            dto.getProducts().forEach(spDto -> {
                Product product = productRepository.getReferenceById(spDto.getProductId());

                ServiceProduct sp = new ServiceProduct();
                sp.setServiceJob(entity);
                sp.setProduct(product);
                sp.setQuantityUsed(spDto.getQuantityUsed());

                entity.getProducts().add(sp);
            });
        }

        // Calcular total
        BigDecimal productsTotal = entity.getProducts().stream()
                .map(ServiceProduct::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        entity.setTotalValue(productsTotal.add(
                entity.getServiceValue() != null ? entity.getServiceValue() : BigDecimal.ZERO
        ));
    }


}