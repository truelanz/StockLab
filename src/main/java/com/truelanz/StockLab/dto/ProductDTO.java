package com.truelanz.StockLab.dto;

import java.time.Instant;

import com.truelanz.StockLab.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Integer currentQuantity;
    private Double productValue;
    private Instant issuanceDate;
    private CategoryDTO category;

    //Construtor sem categoria
    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.currentQuantity = entity.getCurrentQuantity();
        this.productValue = entity.getProductValue();
        this.issuanceDate = entity.getIssuanceDate();
        this.category = new CategoryDTO(entity.getCategory());
    }
}
