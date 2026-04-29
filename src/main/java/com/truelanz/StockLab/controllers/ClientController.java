package com.truelanz.StockLab.controllers;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.truelanz.StockLab.dto.ClientDTO;
import com.truelanz.StockLab.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> SearchPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.SearchPaged(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Cadastra cliente com foto opcional.
     * Content-Type: multipart/form-data
     *   - part "client"  → JSON com os dados (application/json)
     *   - part "photo"   → arquivo de imagem (opcional)
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClientDTO> insert(
            @Valid @RequestPart("client") ClientDTO dto,
            @RequestPart(value = "photo", required = false) MultipartFile photo)
            throws IOException {

        System.out.println("CONTROLLER INSERT");

        ClientDTO created = service.insert(dto, photo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    /**
     * Atualiza cliente com foto opcional.
     * Mesma estrutura multipart do insert.
     * Se "photo" não for enviado, a foto anterior é mantida.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClientDTO> update(
            @PathVariable Long id,
            @Valid @RequestPart("client") ClientDTO dto,
            @RequestPart(value = "photo", required = false) MultipartFile photo)
            throws IOException {

        return ResponseEntity.ok(service.update(id, dto, photo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}