package com.truelanz.StockLab.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private BigDecimal  serviceValue;
    private BigDecimal  totalValue;
    @Enumerated(EnumType.STRING)
    private ServiceStatus status = ServiceStatus.IN_PROGRESS;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @OneToMany(mappedBy = "id.serviceJob", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceProduct> products = new ArrayList<>();

    // Muitos serviços pertencem a um cliente
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
