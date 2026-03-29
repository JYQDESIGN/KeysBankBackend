package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.*;

import java.util.List;

public interface BackupRepository {

    Account findAccount(Long accountId);

    List<UserAccountProfile> findProfiles(Long accountId);

    List<CreditCard> findCreditCards(List<Long> profileIds);

    List<CheckBook> findCheckBooks(List<Long> profileIds);

    List<OperationType> findOperationTypes(Long accountId);

    List<OperationMode> findOperationModes(Long accountId);

    List<ModeKey> findModeKeys(List<Long> modeIds);

    List<Category> findCategories(Long accountId);

    List<SubCategory> findSubCategories(List<String> categoryIds);

    List<SubCategoryKey> findSubCategoryKeys(List<String> subCategoryIds);

    List<Operation> findOperationsByAccountAndYear(long accountId, long year);

    Balance findBalanceByAccountAndYear(long accountId, long year);
}


