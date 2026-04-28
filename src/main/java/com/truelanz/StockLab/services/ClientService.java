package com.truelanz.StockLab.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

    // ── Pesquisa paginada ─────────────────────────────────────────────────────
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

    // ── Insert ────────────────────────────────────────────────────────────────
/*     @Transactional
    public ClientDTO insert(ClientDTO dto, MultipartFile photo) throws IOException {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity.setDateRegister(Instant.now()); // só no insert

        if (photo != null && !photo.isEmpty()) {
            entity.setPhoto(processPhoto(photo));
        }

        repository.save(entity);
        return new ClientDTO(entity);
    } */

        @Transactional
    public ClientDTO insert(ClientDTO dto, MultipartFile photo) throws IOException {

        Client entity = new Client();
        copyDtoToEntity(dto, entity);

        if (photo != null && !photo.isEmpty()) {
            entity.setPhoto(photo.getBytes());
        }

        System.out.println("TIPO PHOTO: " + 
            (entity.getPhoto() != null ? entity.getPhoto().getClass() : "null"));
        entity = repository.save(entity);

        return new ClientDTO(entity);
    }

    // ── Update ────────────────────────────────────────────────────────────────
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto, MultipartFile photo) throws IOException {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            // dateRegister NÃO é atualizado — mantém data original de cadastro

            if (photo != null && !photo.isEmpty()) {
                entity.setPhoto(processPhoto(photo));
            }
            // Se photo vier null/vazia → mantém foto existente no banco

            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client id: " + id + " not found");
        }
    }

    // ── Delete ────────────────────────────────────────────────────────────────
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

    // ── Validação e leitura da foto ───────────────────────────────────────────
    private byte[] processPhoto(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        if (bytes.length == 0) {
            throw new IllegalArgumentException("Arquivo de imagem está vazio");
        }

        // CORREÇÃO PRINCIPAL: detecta o tipo pelos magic bytes do arquivo,
        // ignorando o Content-Type do header — o Insomnia (e alguns clientes)
        // envia 'application/octet-stream' por padrão, o que causava 500.
        if (!isValidImageBytes(bytes)) {
            throw new IllegalArgumentException("Formato inválido. Aceitos: JPEG, PNG, WEBP");
        }

        if (bytes.length > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("Imagem deve ter no máximo 5 MB");
        }

        return bytes;
    }

    /**
     * Verifica o tipo real do arquivo pelos primeiros bytes (magic bytes),
     * independente do Content-Type declarado pelo cliente.
     *
     * JPEG : FF D8 FF
     * PNG  : 89 50 4E 47
     * WEBP : RIFF....WEBP
     */
    private boolean isValidImageBytes(byte[] bytes) {
        if (bytes.length < 4) return false;

        // JPEG
        if ((bytes[0] & 0xFF) == 0xFF &&
            (bytes[1] & 0xFF) == 0xD8 &&
            (bytes[2] & 0xFF) == 0xFF) {
            return true;
        }

        // PNG
        if ((bytes[0] & 0xFF) == 0x89 &&
            (bytes[1] & 0xFF) == 0x50 &&   // P
            (bytes[2] & 0xFF) == 0x4E &&   // N
            (bytes[3] & 0xFF) == 0x47) {   // G
            return true;
        }

        // WEBP: "RIFF" nos bytes 0-3, "WEBP" nos bytes 8-11
        if (bytes.length >= 12 &&
            bytes[0] == 'R' && bytes[1] == 'I' &&
            bytes[2] == 'F' && bytes[3] == 'F' &&
            bytes[8] == 'W' && bytes[9] == 'E' &&
            bytes[10] == 'B' && bytes[11] == 'P') {
            return true;
        }

        return false;
    }

    // ── Cópia de campos DTO → Entidade ────────────────────────────────────────
    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setBirth(dto.getBirth());
        entity.setLocalAddress(dto.getLocalAddress());
        entity.setCPF(dto.getCPF());
        entity.setCep(dto.getCep());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
        entity.setHealthPlan(dto.getHealthPlan());
        entity.setEmail(dto.getEmail());
        // dateRegister → tratado em insert() com Instant.now()
        // photo        → tratado separadamente via processPhoto()
    }
}