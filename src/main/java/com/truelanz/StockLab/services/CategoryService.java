package com.truelanz.StockLab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.StockLab.dto.CategoryDTO;
import com.truelanz.StockLab.entities.Category;
import com.truelanz.StockLab.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> result = repository.findAll(pageable);
        return result.map(x -> new CategoryDTO(x));
    }
}
