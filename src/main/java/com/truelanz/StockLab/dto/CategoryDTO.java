package com.truelanz.StockLab.dto;

import com.truelanz.StockLab.entities.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CategoryDTO {

    private Long id;
    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 3, max = 50, message = "O nome da categoria deve ter entre 3 e 50 caracteres")
    private String name;

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
