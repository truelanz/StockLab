package com.truelanz.StockLab.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import com.truelanz.StockLab.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Integer currentQuantity;
    private BigDecimal productValue;
    private Instant issuanceDate;
    private String imgProduct;
    private CategoryDTO category;
    private LocalDate validity;

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.currentQuantity = entity.getCurrentQuantity();
        this.productValue = entity.getProductValue();
        this.issuanceDate = entity.getIssuanceDate();
        this.imgProduct = entity.getImgProduct();
        if (entity.getCategory() != null) {
            this.category = new CategoryDTO(entity.getCategory());
        }
        this.validity = entity.getValidity();
    }
}
