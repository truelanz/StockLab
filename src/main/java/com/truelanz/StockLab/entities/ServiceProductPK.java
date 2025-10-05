package com.truelanz.StockLab.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class ServiceProductPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceJob serviceJob;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

