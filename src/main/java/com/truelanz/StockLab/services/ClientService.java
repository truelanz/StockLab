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

    // No ClientService.java
    @Transactional
    public ClientDTO uploadImage(Long id, MultipartFile file) throws IOException {
        // 1. Valida se o cliente existe ANTES de processar o arquivo
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client id: " + id + " not found"));

        String originalName = file.getOriginalFilename();
        if (originalName == null) throw new IllegalArgumentException("Arquivo inválido.");
        
        String ext = originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();
        if (!List.of("jpg", "jpeg", "png").contains(ext)) {
            throw new IllegalArgumentException("Formato inválido.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").withZone(ZoneId.systemDefault());
        String timestamp = formatter.format(Instant.now());

        String safeName = entity.getName().replaceAll("[^a-zA-Z0-9]", "_");
        String fileName = "ID" + id + "_" + safeName + "_" + timestamp + "." + ext;

        // 2. Garante a criação do diretório
        Path dir = Paths.get("C:/StockLab/imagens");
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        Path destination = dir.resolve(fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        // 3. Atualiza o path e salva
        entity.setPathImg(destination.toString());
        entity = repository.save(entity);

        return new ClientDTO(entity);
    }

/*     @Transactional
    public ClientDTO updateImage(Long id, MultipartFile file) throws IOException {

        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client id: " + id + " not found"));

        //Remove imagem antiga
        if (entity.getPathImg() != null) {
            Path oldPath = Paths.get(entity.getPathImg());
            Files.deleteIfExists(oldPath);
        }

        //Validação
        String originalName = file.getOriginalFilename();
        if (originalName == null || !originalName.contains(".")) {
            throw new IllegalArgumentException("Arquivo inválido.");
        }

        String ext = originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();
        if (!List.of("jpg", "jpeg", "png").contains(ext)) {
            throw new IllegalArgumentException("Formato inválido.");
        }

        //Nome seguro
        String safeName = entity.getName().replaceAll("[^a-zA-Z0-9]", "_");

        String fileName = safeName + "_" + System.currentTimeMillis() + "." + ext;

        Path dir = Paths.get("C:/StockLab/imagens");
        Files.createDirectories(dir);

        Path destination = dir.resolve(fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        entity.setPathImg(destination.toString());
        entity = repository.save(entity);

        return new ClientDTO(entity);
    }
 */

    @Transactional
    public void deleteImage(Long id) throws IOException {

        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client id: " + id + " not found"));

        if (entity.getPathImg() != null) {
            Path path = Paths.get(entity.getPathImg());
            Files.deleteIfExists(path);

            entity.setPathImg(null);
            repository.save(entity);
        }
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
        entity.setPathImg(dto.getPathImg());
    }
}