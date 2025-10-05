package com.truelanz.StockLab.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.truelanz.StockLab.dto.ServiceJobDTO;
import com.truelanz.StockLab.services.ServiceJobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/services")
public class ServiceJobController {

    @Autowired
    private ServiceJobService service;

    @GetMapping()
    public ResponseEntity<Page<ServiceJobDTO>> findAll(Pageable pageable) { //Parametros: page, size, sort
        Page <ServiceJobDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping()
    public ResponseEntity<ServiceJobDTO> insert(@Valid @RequestBody ServiceJobDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceJobDTO> update(@Valid @PathVariable Long id, @RequestBody ServiceJobDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<ServiceJobDTO> finishService(@PathVariable Long id) {
        ServiceJobDTO dto = service.finishService(id);
        return ResponseEntity.ok(dto);
    }
}