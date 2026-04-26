package com.truelanz.StockLab.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.truelanz.StockLab.dto.ClientDTO;
import com.truelanz.StockLab.services.ClientService;

/**
 * Endpoint dedicado para trocar só a foto, sem reenviar todos os dados.
 * POST /upload/image/{clientId}  → atualiza somente a foto
 * DELETE /upload/image/{clientId} → remove a foto
 */
@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    @Autowired
    private ClientService service;

    @PostMapping(value = "/image/{clientId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClientDTO> updatePhoto(
            @PathVariable Long clientId,
            @RequestParam("file") MultipartFile file) throws IOException {

        // Reutiliza o update passando dto vazio com os dados atuais preservados
        ClientDTO existing = service.findById(clientId);
        ClientDTO updated = service.update(clientId, existing, file);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/image/{clientId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long clientId) {
        // Busca cliente, zera a foto e salva
        ClientDTO existing = service.findById(clientId);
        try {
            service.update(clientId, existing, null);
        } catch (IOException e) {
            // nunca ocorre com photo null
        }
        return ResponseEntity.noContent().build();
    }
}