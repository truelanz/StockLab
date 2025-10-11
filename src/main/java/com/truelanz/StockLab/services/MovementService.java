package com.truelanz.StockLab.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.StockLab.dto.MovementDTO;
import com.truelanz.StockLab.dto.MovementInsertDTO;
import com.truelanz.StockLab.entities.Movement;
import com.truelanz.StockLab.entities.MovementType;
import com.truelanz.StockLab.entities.Product;
import com.truelanz.StockLab.repositories.MovementRepository;
import com.truelanz.StockLab.repositories.ProductRepository;
import com.truelanz.StockLab.services.exceptions.ResourceNotFoundException;

@Service
public class MovementService {

    @Autowired
    private MovementRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<MovementDTO> findAllPaged(Pageable pageable) {
        Page<Movement> result = repository.findAllWithProduct(pageable);
        return result.map(MovementDTO::new);
    }

    @Transactional(readOnly = true)
    public MovementDTO findById(Long id) {
        Movement movement = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movement id: %d not found", id)));

        return new MovementDTO(movement);
    }

     @Transactional
    public MovementDTO insert(MovementInsertDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Atualiza quantidade do produto
        if (dto.getTypeEntryExit() == MovementType.ENTRY) {
            product.setCurrentQuantity(product.getCurrentQuantity() + dto.getQuantity());
        } else if (dto.getTypeEntryExit() == MovementType.EXIT) {
            int newQuantity = product.getCurrentQuantity() - dto.getQuantity();
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Quantidade insuficiente em estoque!");
            }
            product.setCurrentQuantity(newQuantity);
        }

        // Salva movimentação
        Movement movement = new Movement();
        movement.setQuantity(dto.getQuantity());
        movement.setTypeEntryExit(dto.getTypeEntryExit());
        movement.setIssuanceDate(Instant.now());
        movement.setObservation(dto.getObservation());
        movement.setProduct(product);

        movement = repository.save(movement);

        // Atualiza produto
        productRepository.save(product);

        return new MovementDTO(movement);
    }
}
