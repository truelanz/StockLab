package com.truelanz.StockLab.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductInsertDTO {

    private String name;
    private Integer currentQuantity;
    private BigDecimal productValue;
    private String imgProduct;
    private Long categoryId;
}
