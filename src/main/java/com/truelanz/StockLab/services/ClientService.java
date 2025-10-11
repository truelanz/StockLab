package com.truelanz.StockLab.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.StockLab.dto.ClientDTO;
import com.truelanz.StockLab.entities.Client;
import com.truelanz.StockLab.repositories.ClientRepository;
import com.truelanz.StockLab.services.exceptions.DatabaseException;
import com.truelanz.StockLab.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    //Pesquisa paginada e pesquisa por nome
    @Transactional(readOnly = true)
    public Page<ClientDTO> SearchPaged(Pageable pageable, String search) {
        Page<Client> clients;

        if (search != null && !search.isEmpty()) {
            clients = repository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            clients = repository.findAll(pageable);
        }

        // Convertendo para DTO
        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Client id: %d not found: ", id)));

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);

        repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
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

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setBirth(dto.getBirth());
        entity.setDateRegister(Instant.now());
        entity.setLocalAddress(dto.getLocalAddress());
        entity.setCPF(dto.getCPF());
    }
}