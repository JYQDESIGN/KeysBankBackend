package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.*;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;
import com.jyqdesign.keysbankbackend.service.AccountPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preference")
@RequiredArgsConstructor
public class PreferenceController {

    private final AccountPreferenceService accountPreferenceService;

    @GetMapping("/{id}")
    public AccountPreferencesDTO readPreferencesById(@PathVariable long id) {
        System.out.println("Get account preferences by id: "+id);
        return accountPreferenceService.readAccountPreferencesById(id);
    }

    // MODE

    @PutMapping("/mode/{id}")
    public OperationMode updatePreferenceModeById(
            @PathVariable long id,
            @RequestBody OperationMode updatedMode) {
        return accountPreferenceService.updatePreferenceModeById(id, updatedMode);
    }

    // KEY of mode

    @PostMapping("/mode/key")
    public ResponseEntity<ModeKey> createModeKey(@RequestBody ModeKey newKey) {
        ModeKey result = accountPreferenceService.createModeKey(newKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/mode/key/{id}")
    public boolean deleteModeKeyById(@PathVariable long id) {
        return accountPreferenceService.deleteModeKeyById(id);
    }

    // TYPE

    @PutMapping("/type/{id}")
    public OperationType updatePreferenceTypeById(
            @PathVariable long id,
            @RequestBody OperationType updatedType) {
        return accountPreferenceService.updatePreferenceTypeById(id, updatedType);
    }

    // KEY of sub categories

    @PostMapping("/subcat/key")
    public ResponseEntity<SubCategoryKey> createPreferenceKey(@RequestBody SubCategoryKey newKey) {
        SubCategoryKey result = accountPreferenceService.createPreferenceKey(newKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/subcat/keys")
    public ResponseEntity<Integer> createPreferenceKeys(@RequestBody List<SubCategoryKey> newKeys) {
        int result = accountPreferenceService.createPreferenceKeys(newKeys);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/subcat/key/{id}")
    public boolean deletePreferenceKeyById(@PathVariable long id) {
        return accountPreferenceService.deletePreferenceKeyById(id);
    }

    // SUB CATEGORY

    @PostMapping("/subcat")
    public ResponseEntity<SubCategory> createPreferenceKey(@RequestBody SubCategory newSubCategory) {
        SubCategory result = accountPreferenceService.createPreferenceSubCategory(newSubCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/subcat/{id}")
    public SubCategory updatePreferenceSubCategoryById(
            @PathVariable long id,
            @RequestBody SubCategory updatedSubCategory) {
        return accountPreferenceService.updatePreferenceSubCategoryById(id, updatedSubCategory);
    }

    @DeleteMapping("/subcat/{id}")
    public boolean deletePreferenceSubCategoryById(@PathVariable long id) {
        return accountPreferenceService.deletePreferenceSubCategoryById(id);
    }
    
    // CATEGORY
    @GetMapping("/categories/{id}")
    public List<Category> readCategoryPreferencesById(@PathVariable long id) {
        System.out.println("Get categories preferences by id: "+id);
        return accountPreferenceService.readCategoryPreferencesById(id);
    }

    @PostMapping("/cat")
    public ResponseEntity<Category> createPreferenceCategory(@RequestBody Category newCategory) {
        Category result = accountPreferenceService.createPreferenceCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/cat/{id}")
    public Category updatePreferenceCategoryById(
            @PathVariable long id,
            @RequestBody Category updatedCategory) {
        return accountPreferenceService.updatePreferenceCategoryById(id, updatedCategory);
    }

    @DeleteMapping("/cat/{id}")
    public boolean deletePreferenceCategoryById(@PathVariable long id) {
        return accountPreferenceService.deletePreferenceCategoryById(id);
    }
}
