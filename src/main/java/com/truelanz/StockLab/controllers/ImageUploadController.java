package com.truelanz.StockLab.controllers;

import com.truelanz.StockLab.dto.ClientDTO;
import com.truelanz.StockLab.services.ClientService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    @Autowired
    private ClientService clientService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/image/{clientId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClientDTO> uploadImage(
            @PathVariable Long clientId,
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            ClientDTO dto = clientService.uploadImage(clientId, file);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/upload/image/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) throws IOException {

        clientService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
    
}