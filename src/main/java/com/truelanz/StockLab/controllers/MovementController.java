package com.truelanz.StockLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truelanz.StockLab.dto.MovementDTO;
import com.truelanz.StockLab.dto.MovementInsertDTO;
import com.truelanz.StockLab.services.MovementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private MovementService service;

    @GetMapping()
    public Page<MovementDTO> findAll(
            Pageable pageable,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
            ) {
        return service.findAllPaged(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDTO> findById(@Valid @PathVariable Long id) {
        MovementDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<MovementDTO> insert(@Valid @RequestBody MovementInsertDTO dto) {
        MovementDTO newMovement = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovement);
    }
}
