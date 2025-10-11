package com.truelanz.StockLab.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.truelanz.StockLab.entities.ServiceJob;

//Buscas dinâmicas com múltiplos filtros
public class ServiceJobSpecification {

    public static Specification<ServiceJob> nameContains(String name) {
        return (root, query, cb) ->
                name == null || name.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<ServiceJob> dateGreaterOrEqual(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null : cb.greaterThanOrEqualTo(root.get("initDate"), date);
    }

    public static Specification<ServiceJob> dateLessOrEqual(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null : cb.lessThanOrEqualTo(root.get("finalDate"), date);
    }

    public static Specification<ServiceJob> byClient(Long clientId) {
        return (root, query, cb) ->
                clientId == null ? null : cb.equal(root.get("client").get("id"), clientId);
    }

    public static Specification<ServiceJob> byEmployee(Long employeeId) {
        return (root, query, cb) ->
                employeeId == null ? null : cb.equal(root.get("employee").get("id"), employeeId);
    }
}

