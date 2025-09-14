package com.truelanz.StockLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truelanz.StockLab.dto.ProductDTO;
import com.truelanz.StockLab.services.ProductService;

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
    
}
