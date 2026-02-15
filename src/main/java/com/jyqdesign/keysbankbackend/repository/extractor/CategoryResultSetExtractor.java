package com.jyqdesign.keysbankbackend.repository.extractor;

import com.jyqdesign.keysbankbackend.entity.Category;
import com.jyqdesign.keysbankbackend.entity.SubCategory;
import com.jyqdesign.keysbankbackend.entity.SubCategoryKey;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CategoryResultSetExtractor implements ResultSetExtractor<List<Category>> {

    @Override
    public List<Category> extractData(ResultSet rs) throws SQLException {

        // Map pour éviter doublons de categories
        Map<String, Category> categoryMap = new LinkedHashMap<>();

        // Map pour éviter doublons de subCategories
        Map<String, SubCategory> subCategoryMap = new HashMap<>();

        while (rs.next()) {

            // CATEGORY

            String categoryId = rs.getString("idCategory");

            Category category = categoryMap.get(categoryId);

            if (category == null) {

                category = new Category();
                category.setIdCategory(categoryId);
                category.setLabel(rs.getString("cLabel"));
                category.setColor(rs.getString("cColor"));
                category.setType(rs.getString("cType"));
                category.setSubCategories(new ArrayList<>());

                categoryMap.put(categoryId, category);
            }

            // SUB CATEGORY

            String subCategoryId = rs.getString("idSubCategory");

            SubCategory subCategory = null;

            if (subCategoryId != null) {

                subCategory = subCategoryMap.get(subCategoryId);

                if (subCategory == null) {

                    subCategory = new SubCategory();

                    subCategory.setIdSubCategory(subCategoryId);
                    subCategory.setIdCategory(categoryId);
                    subCategory.setLabel(rs.getString("scLabel"));
                    subCategory.setColor(rs.getString("scColor"));
                    subCategory.setType(rs.getString("scType"));
                    subCategory.setKeys(new ArrayList<>());

                    subCategoryMap.put(subCategoryId, subCategory);

                    // associer à la category
                    category.getSubCategories().add(subCategory);
                }
            }

            // KEY

            String keyLabel = rs.getString("kLabel");
            long keyId = rs.getLong("idKey");
            System.out.println("KEY: "+keyLabel+' '+keyId);
            if (keyLabel != null && subCategory != null) {

                subCategory.getKeys().add(new SubCategoryKey(keyId, keyLabel));
            }
        }
        System.out.println("PREFERENCES: "+categoryMap.values());
        return new ArrayList<>(categoryMap.values());
    }
}