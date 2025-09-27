package com.truelanz.StockLab.dto;

import com.truelanz.StockLab.entities.MovementType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MovementInsertDTO {

    private Integer quantity;
    private MovementType typeEntryExit;
    private String observation;
    private Long productId;

}
