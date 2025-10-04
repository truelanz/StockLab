package com.truelanz.StockLab.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.truelanz.StockLab.entities.ServiceJob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ServiceJobDTO {

    private Long id;
    private String name;
    private String description;
    private LocalTime initTime;
    private LocalTime finalTime;
    private LocalDate initDate;
    private LocalDate finalDate;

    private EmployeeDTO employee;
    private List<ProductMinDTO> products;
    private ClientMinDTO client;

    public ServiceJobDTO(ServiceJob entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.initTime = entity.getInitTime();
        this.finalTime = entity.getFinalTime();
        this.initDate = entity.getInitDate();
        this.finalDate = entity.getFinalDate();

        if (entity.getEmployee() != null) {
            this.employee = new EmployeeDTO(entity.getEmployee());
        }

        if (entity.getClient() != null) {
            this.client = new ClientMinDTO(entity.getClient());
        }

        if (entity.getProducts() != null) {
            this.products = entity.getProducts()
                                  .stream()
                                  .map(ProductMinDTO::new)
                                  .toList();
        }
    }
}
