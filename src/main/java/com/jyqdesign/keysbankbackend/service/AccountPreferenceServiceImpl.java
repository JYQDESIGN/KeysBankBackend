package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.*;
import com.jyqdesign.keysbankbackend.repository.AccountPreferenceRepository;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountPreferenceServiceImpl implements AccountPreferenceService {

    AccountPreferenceRepository accountPreferenceRepository;

    public AccountPreferenceServiceImpl(AccountPreferenceRepository accountPreferenceRepository) {
        this.accountPreferenceRepository = accountPreferenceRepository;
    }

    //ALL
    @Override
    public AccountPreferencesDTO readAccountPreferencesById(long id) {
        return this.accountPreferenceRepository.readAccountPreferencesById(id);
    }

    //MODE
    @Override
    public List<OperationMode> readPreferenceModes(long idAccount) {
        return this.accountPreferenceRepository.readPreferenceModes(idAccount);
    }

    @Override
    public void createDefaultModes(long idAccount) {
        this.accountPreferenceRepository.createDefaultModes(idAccount);
    }

    @Override
    public OperationMode updatePreferenceModeById(long id, OperationMode updatedMode) {
        return this.accountPreferenceRepository.updatePreferenceModeById(id, updatedMode);
    }

    //TYPE
    @Override
    public List<OperationType> readPreferenceTypes(long idAccount) {
        return this.accountPreferenceRepository.readPreferenceTypes(idAccount);
    }

    @Override
    public void createDefaultTypes(long idAccount) {
        this.accountPreferenceRepository.createDefaulTypes(idAccount);
    }

    @Override
    public OperationType updatePreferenceTypeById(long id, OperationType updatedType) {
        return this.accountPreferenceRepository.updatePreferenceTypeById(id, updatedType);
    }



    //CATEGORY
    @Override
    public void createDefaultCategories(long idAccount) {
        this.accountPreferenceRepository.createDefaultCategories(idAccount);
    }

    @Override
    public List<Category> readPreferenceCategories(long idAccount) {
        return this.accountPreferenceRepository.readPreferenceCategories(idAccount);
    }

    @Override
    public Category createPreferenceCategory(Category newCategory) {
        return this.accountPreferenceRepository.createPreferenceCategory(newCategory);
    }

    @Override
    public Category updatePreferenceCategoryById(long id, Category updatedCategory) {
        return this.accountPreferenceRepository.updatePreferenceCategoryById(id, updatedCategory);
    }

    @Override
    public boolean deletePreferenceCategoryById(long id) {
        return this.accountPreferenceRepository.deletePreferenceCategoryById(id);
    }

    //SUB CATEGORY
    @Override
    public SubCategory createPreferenceSubCategory(SubCategory newSubCategory) {
        return this.accountPreferenceRepository.createPreferenceSubCategory(newSubCategory);
    }

    @Override
    public SubCategory updatePreferenceSubCategoryById(long id, SubCategory updatedSubCategory) {
        return this.accountPreferenceRepository.updatePreferenceSubCategoryById(id, updatedSubCategory);
    }

    @Override
    public boolean deletePreferenceSubCategoryById(long id) {
        return this.accountPreferenceRepository.deletePreferenceSubCategoryById(id);
    }

    //KEY
    @Override
    public SubCategoryKey createPreferenceKey(SubCategoryKey newKey) {
        return this.accountPreferenceRepository.createPreferenceKey(newKey);
    }

    @Override
    public boolean deletePreferenceKeyById(long id) {
        return this.accountPreferenceRepository.deletePreferenceKeyById(id);
    }
}
