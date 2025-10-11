package com.truelanz.StockLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.truelanz.StockLab.dto.EmployeeDTO;
import com.truelanz.StockLab.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /* @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> findAll(Pageable pageable) { //Parametros: page, size, sort
        Page <EmployeeDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    } */

    //Pesquisa paginada com pesquisa por nome.
    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> SearchPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok().body(service.SearchPaged(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@Valid @PathVariable Long id) {
        EmployeeDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> insert(@Valid @RequestBody EmployeeDTO dto) {
        EmployeeDTO newCategory = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@Valid @PathVariable Long id, @RequestBody EmployeeDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
