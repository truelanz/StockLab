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

import com.truelanz.StockLab.dto.ProductDTO;
import com.truelanz.StockLab.dto.ProductInsertDTO;
import com.truelanz.StockLab.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) { //Parametros: page, size, sort
        Page <ProductDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }
    
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductInsertDTO dto) {
        ProductDTO newProduct = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable Long id, @RequestBody ProductInsertDTO dto) {
        ProductDTO updatedProduct = service.update(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
