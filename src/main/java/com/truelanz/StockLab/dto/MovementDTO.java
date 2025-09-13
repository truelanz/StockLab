package com.truelanz.StockLab.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class MovementDTO {

    private Long id;
    private Integer quantity;
    private String typeEntryExit;
    private LocalDateTime issuanceDate;
    private String observation;
    private ProductDTO product;
}
