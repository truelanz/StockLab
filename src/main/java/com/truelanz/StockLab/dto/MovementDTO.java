package com.truelanz.StockLab.dto;

import java.time.Instant;

import com.truelanz.StockLab.entities.Movement;
import com.truelanz.StockLab.entities.MovementType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MovementDTO {

    private Long id;
    private Integer quantity;
    private MovementType typeEntryExit;
    private Instant issuanceDate;
    private String observation;
    private ProductMinDTO product;

    public MovementDTO(Movement entity) {
        this.id = entity.getId();
        this.quantity = entity.getQuantity();
        this.typeEntryExit = entity.getTypeEntryExit();
        this.issuanceDate = entity.getIssuanceDate();
        this.observation = entity.getObservation();
        this.product = new ProductMinDTO(entity.getProduct());
    }

}
