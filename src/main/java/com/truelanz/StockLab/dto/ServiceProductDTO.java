package com.truelanz.StockLab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ServiceProductDTO {
    private Long productId;
    private Integer quantityUsed;
}
