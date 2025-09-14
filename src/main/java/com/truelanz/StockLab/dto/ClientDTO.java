package com.truelanz.StockLab.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.truelanz.StockLab.entities.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ClientDTO {

    private Long id;
    private String name;
    private LocalDate birth;
    private String phone;
    private Instant dateRegister;

    public ClientDTO(Client entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.birth = entity.getBirth();
    this.phone = entity.getPhone();
    this.dateRegister = entity.getDateRegister();
    }
}
