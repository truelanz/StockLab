package com.truelanz.StockLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /* @GetMapping()
    public ResponseEntity<Page<MovementDTO>> findAll(Pageable pageable) { //Parametros: page, size, sort
        Page <MovementDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    } */

    @GetMapping()
    public Page<MovementDTO> findAll(Pageable pageable) {
        return service.findAllPaged(pageable);
    }

    @PostMapping
    public ResponseEntity<MovementDTO> insert(@Valid @RequestBody MovementInsertDTO dto) {
        MovementDTO newMovement = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovement);
    }
}
