package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.*;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;

import java.util.List;

public interface AccountPreferenceRepository {

    //============================================================================
    // ALL
    //============================================================================

    AccountPreferencesDTO readAccountPreferencesById(long idAccount);

    //============================================================================
    // CRUD mode
    //============================================================================

    void createDefaultModes(long idAccount);
    List<OperationMode> readPreferenceModes(long idAccount);
    OperationMode updatePreferenceModeById(long id, OperationMode updatedMode);

    //============================================================================
    // CRUD type
    //============================================================================

    void createDefaulTypes(long idAccount);
    List<OperationType> readPreferenceTypes(long idAccount);
    OperationType updatePreferenceTypeById(long id, OperationType updatedType);

    //============================================================================
    // CRUD category
    //============================================================================

    void createDefaultCategories(long idAccount);
    List<Category> readPreferenceCategories(long idAccount);
    SubCategoryKey createPreferenceKey(SubCategoryKey newKey);
    boolean deletePreferenceKeyById(long id);

    //============================================================================
    // CRUD sub category
    //============================================================================

    Category createPreferenceCategory(Category newCategory);
    Category updatePreferenceCategoryById(long id, Category updatedCategory);
    boolean deletePreferenceCategoryById(long id);

    //============================================================================
    // CRUD key
    //============================================================================

    SubCategory createPreferenceSubCategory(SubCategory newSubCategory);
    SubCategory updatePreferenceSubCategoryById(long id, SubCategory updatedSubCategory);
    boolean deletePreferenceSubCategoryById(long id);
}
