package com.truelanz.StockLab.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.StockLab.dto.ProductDTO;
import com.truelanz.StockLab.dto.ProductInsertDTO;
import com.truelanz.StockLab.entities.Category;
import com.truelanz.StockLab.entities.Product;
import com.truelanz.StockLab.repositories.CategoryRepository;
import com.truelanz.StockLab.repositories.ProductRepository;
import com.truelanz.StockLab.services.exceptions.DatabaseException;
import com.truelanz.StockLab.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductInsertDTO dto) {
        Product product = new Product();
        copyToEntity(dto, product);
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductInsertDTO dto) {
        try {
            Product entity = repository.getReferenceById(id); // busca sem ir ao banco de imediato
            copyToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product id not found: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
         if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

/*     @Transactional
    public void inactiveProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        // inativa em vez de deletar
        product.setStatus(ProductStatus.INACTIVE);
        repository.save(product);
    } */

    public void copyToEntity(ProductInsertDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setCurrentQuantity(dto.getCurrentQuantity());
        entity.setProductValue(dto.getProductValue());
        entity.setIssuanceDate(Instant.now());
        entity.setImgProduct(dto.getImgProduct());
        entity.setValidity(dto.getValidity());

        // Busca a categoria correta
        Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        entity.setCategory(category);
    }

}
