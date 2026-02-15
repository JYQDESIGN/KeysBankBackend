package com.jyqdesign.keysbankbackend.entity;

import lombok.Data;
@Data
public class SubCategoryKey {
    private long idSubCategory;
    private long idKey;
    private String key;

    public SubCategoryKey() {
    }

    public SubCategoryKey(long idKey, String key) {
        this.idKey = idKey;
        this.key = key;
    }
}
