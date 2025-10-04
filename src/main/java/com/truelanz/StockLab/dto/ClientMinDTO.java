package com.truelanz.StockLab.dto;

import com.truelanz.StockLab.entities.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ClientMinDTO {

    private Long id;
    private String name;

    public ClientMinDTO(Client entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    }
}
