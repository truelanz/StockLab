package com.truelanz.StockLab.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truelanz.StockLab.dto.CategoryDTO;
import com.truelanz.StockLab.dto.ProductDTO;
import com.truelanz.StockLab.repositories.ProductRepository;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(p -> new ProductDTO(
                        p.getId(),
                        p.getName(),
                        p.getCurrentQuantity(),
                        p.getProductValue(),
                        p.getIssuanceDate(),
                        new CategoryDTO(
                                p.getCategory().getId(),
                                p.getCategory().getName()
                        )
                ))
                .collect(Collectors.toList());
    }
    
}
