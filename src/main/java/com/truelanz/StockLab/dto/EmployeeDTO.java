package com.truelanz.StockLab.dto;

import com.truelanz.StockLab.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class EmployeeDTO {

    private Long id;
    private String name;

    //Construtor sem categoria
    public EmployeeDTO(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
