package com.truelanz.StockLab.entities;

public enum ServiceStatus {
    PENDING,       // serviço criado mas não iniciado
    IN_PROGRESS,   // serviço em andamento
    FINALIZED,     // serviço finalizado
    CANCELLED      // serviço cancelado
}
