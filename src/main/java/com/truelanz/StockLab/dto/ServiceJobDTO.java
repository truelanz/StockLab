package com.truelanz.StockLab.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.truelanz.StockLab.entities.ServiceJob;
import com.truelanz.StockLab.entities.ServiceStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ServiceJobDTO {

    private Long id;
    @NotBlank(message = "O nome do serviço é obrigatório")
    private String name;
    private String description;
    private LocalTime initTime;
    private LocalTime finalTime;
    private LocalDate initDate;
    private LocalDate finalDate;
    private BigDecimal serviceValue;
    private BigDecimal totalValue;
    private ServiceStatus status;

    private EmployeeDTO employee;
    private List<ServiceProductDTO> products = new ArrayList<>();
    private ClientMinDTO client;

    public ServiceJobDTO(ServiceJob entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.initTime = entity.getInitTime();
        this.finalTime = entity.getFinalTime();
        this.initDate = entity.getInitDate();
        this.finalDate = entity.getFinalDate();
        this.serviceValue = entity.getServiceValue();
        this.totalValue = entity.getTotalValue();
        this.status = entity.getStatus();

        if (entity.getEmployee() != null) {
            this.employee = new EmployeeDTO(entity.getEmployee());
        }

        if (entity.getClient() != null) {
            this.client = new ClientMinDTO(entity.getClient());
        }

        if (entity.getProducts() != null) {
            this.products = entity.getProducts()
                .stream()
                .map(sp -> new ServiceProductDTO(sp.getProduct().getId(), sp.getQuantityUsed()))
                .toList();
        }
    }
}
