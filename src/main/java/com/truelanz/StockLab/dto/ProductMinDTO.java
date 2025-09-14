package com.truelanz.StockLab.dto;

import com.truelanz.StockLab.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductMinDTO {

    private Long id;
    private String name;

    //Construtor sem categoria
    public ProductMinDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
