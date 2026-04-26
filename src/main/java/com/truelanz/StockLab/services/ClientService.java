package com.truelanz.StockLab.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png", "image/webp");

    // ── Pesquisa paginada ──────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public Page<ClientDTO> SearchPaged(Pageable pageable, String search) {
        Page<Client> clients = (search != null && !search.isBlank())
                ? repository.findByNameContainingIgnoreCase(search, pageable)
                : repository.findAll(pageable);
        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client id: " + id + " not found"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto, MultipartFile photo) throws IOException {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        if (photo != null && !photo.isEmpty()) {
            entity.setPhoto(processPhoto(photo));
        }
        repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto, MultipartFile photo) throws IOException {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            if (photo != null && !photo.isEmpty()) {
                // Substitui a foto anterior
                entity.setPhoto(processPhoto(photo));
            }
            // Se photo == null ou vazia, mantém a foto existente
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client id: " + id + " not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Client id: " + id + " not found");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

    private byte[] processPhoto(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new IllegalArgumentException(
                    "Formato inválido. Aceitos: JPEG, PNG, WEBP");
        }
        if (file.getSize() > 5 * 1024 * 1024) { // 5 MB
            throw new IllegalArgumentException("Imagem deve ter no máximo 5 MB");
        }
        return file.getBytes();
    }


    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setBirth(dto.getBirth());
        entity.setDateRegister(Instant.now());
        entity.setLocalAddress(dto.getLocalAddress());
        entity.setCPF(dto.getCPF());
        entity.setCep(dto.getCep());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
        entity.setHealthPlan(dto.getHealthPlan());
        entity.setEmail(dto.getEmail());
    }
}