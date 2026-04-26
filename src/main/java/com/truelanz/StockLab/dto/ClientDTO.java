package com.truelanz.StockLab.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Base64;

import com.truelanz.StockLab.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ClientDTO {

    private Long id;
    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 3, max = 50, message = "O nome da categoria deve ter entre 3 e 50 caracteres")
    private String name;
    private LocalDate birth;
    private String phone;
    private Instant dateRegister;
    private String localAddress;
    private String CPF;
    private String cep;
    private String neighborhood;
    private String state;
    private String city;
    private String healthPlan;
    private String email;
    // Imagem em Base64 — só no DTO, nunca na entidade
    private String photoBase64;

    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.phone = entity.getPhone();
        this.dateRegister = entity.getDateRegister();
        this.localAddress = entity.getLocalAddress();
        this.CPF = entity.getCPF();
        this.cep = entity.getCep();
        this.neighborhood = entity.getNeighborhood();
        this.state = entity.getState();
        this.city = entity.getCity();
        this.healthPlan = entity.getHealthPlan();
        this.email = entity.getEmail();
        if (entity.getPhoto() != null) {
            this.photoBase64 = Base64.getEncoder().encodeToString(entity.getPhoto());
        }
    }
    
    public ClientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
