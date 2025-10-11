package com.truelanz.StockLab.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServiceJobSearchDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long clientId;
    private Long employeeId;
}
