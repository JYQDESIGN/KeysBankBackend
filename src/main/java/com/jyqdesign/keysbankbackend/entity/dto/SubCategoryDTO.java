package com.jyqdesign.keysbankbackend.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubCategoryDTO {
    private Long id;
    private String label;
    private String type;
    private String color;
    private List<String> keys;
}
