package com.truelanz.StockLab.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductInsertDTO {
    
    @NotBlank(message = "O nome do produto é obrigatório")
    private String name;
    @NotNull(message = "A quantidade atual é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer currentQuantity;
    @NotNull(message = "O valor do produto é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal productValue;
    private String imgProduct;
    @NotNull(message = "A categoria é obrigatória")
    private Long categoryId;
}
