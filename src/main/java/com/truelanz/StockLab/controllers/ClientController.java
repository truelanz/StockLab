package com.truelanz.StockLab.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.truelanz.StockLab.dto.ClientDTO;
import com.truelanz.StockLab.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    /* @GetMapping()
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) { //Parametros: page, size, sort
        Page <ClientDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    } */

    //Pesquisa paginada com pesquisa por nome.
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> SearchPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok().body(service.SearchPaged(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@Valid @PathVariable Long id) {
        ClientDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> insert(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}