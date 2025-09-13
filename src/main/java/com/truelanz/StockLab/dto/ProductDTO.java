package com.truelanz.StockLab.dto;

import java.time.Instant;
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
}
