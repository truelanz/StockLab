package com.truelanz.StockLab.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_service")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "service_seq", sequenceName = "service_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String description;
    private LocalTime initTime;
    private LocalTime finalTime;
    private LocalDate initDate;
    private LocalDate finalDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @ManyToMany
    @JoinTable(name = "tb_service_product",
        joinColumns = @JoinColumn(name = "service_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    // Muitos serviços pertencem a um cliente
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
