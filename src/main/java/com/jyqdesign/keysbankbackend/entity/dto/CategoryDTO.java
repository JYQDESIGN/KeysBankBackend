package com.jyqdesign.keysbankbackend.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private Long idAccount;
    private String label;
    private String type;
    private String color;
    private List<SubCategoryDTO> subCategories;
}
