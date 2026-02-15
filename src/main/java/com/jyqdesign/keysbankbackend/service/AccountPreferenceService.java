package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.*;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;

import java.util.List;

public interface AccountPreferenceService {
    //READ ALL
    AccountPreferencesDTO readAccountPreferencesById(long id);

    //CRUD type
    void createDefaultTypes(long idAccount);
    List<OperationType> readPreferenceTypes(long idAccount);

    //CRUD mode
    void createDefaultModes(long idAccount);
    List<OperationMode> readPreferenceModes(long idAccount);
    OperationMode updatePreferenceModeById(long id, OperationMode updatedMode);

    //CRUD key
    SubCategoryKey createPreferenceKey(SubCategoryKey newKey);
    OperationType updatePreferenceTypeById(long id, OperationType updatedType);
    boolean deletePreferenceKeyById(long id);

    //CRUD sub category
    SubCategory createPreferenceSubCategory(SubCategory newSubCategory);
    SubCategory updatePreferenceSubCategoryById(long id, SubCategory updatedSubCategory);
    boolean deletePreferenceSubCategoryById(long id);

    //CRUD category
    void createDefaultCategories(long idAccount);
    List<Category> readPreferenceCategories(long idAccount);
    Category createPreferenceCategory(Category newCategory);
    Category updatePreferenceCategoryById(long id, Category updatedCategory);
    boolean deletePreferenceCategoryById(long id);
}
