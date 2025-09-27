package com.truelanz.StockLab.dto;

import com.truelanz.StockLab.entities.MovementType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MovementInsertDTO {

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private Integer quantity;
    @NotNull(message = "O tipo de movimentação é obrigatório")
    private MovementType typeEntryExit;
    private String observation;
    private Long productId;

}
