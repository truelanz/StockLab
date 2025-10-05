package com.truelanz.StockLab.entities;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_service_product")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceProduct {

    @EmbeddedId
    private ServiceProductPK id = new ServiceProductPK();
    private Integer quantityUsed;

    public ServiceJob getServiceJob() {
        return id.getServiceJob();
    }

    public void setServiceJob(ServiceJob serviceJob) {
        id.setServiceJob(serviceJob);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    // Converte para BigDecimal e multiplica valor dos produtos pela quantidade.
    public BigDecimal getSubTotal() {
        if (quantityUsed == null || getProduct() == null || getProduct().getProductValue() == null) {
            return BigDecimal.ZERO;
        }
        return getProduct().getProductValue().multiply(BigDecimal.valueOf(quantityUsed));
    }
    
}
