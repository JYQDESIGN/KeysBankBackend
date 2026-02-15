package com.jyqdesign.keysbankbackend.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private String id;
    private String label;
    private String type;
    private String color;
    private List<SubCategoryDTO> subCategories;
}
