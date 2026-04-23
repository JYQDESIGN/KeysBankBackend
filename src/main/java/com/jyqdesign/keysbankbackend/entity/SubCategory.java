package com.jyqdesign.keysbankbackend.entity;
import java.util.List;
import lombok.Data;
@Data
public class SubCategory {
    private Long idSubCategory;
    private Long idCategory;
    private String label;
    private String type;
    private String color;

    private List<SubCategoryKey> keys;
}
