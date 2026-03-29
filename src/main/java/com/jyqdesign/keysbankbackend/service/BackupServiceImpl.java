package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.*;
import com.jyqdesign.keysbankbackend.entity.dto.BackupOperationDTO;
import com.jyqdesign.keysbankbackend.entity.dto.BackupPreferenceDTO;
import com.jyqdesign.keysbankbackend.repository.BackupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BackupServiceImpl implements BackupService {

    private final BackupRepository repository;

    public BackupServiceImpl(BackupRepository repository) {
        this.repository = repository;
    }

    @Override
    public BackupPreferenceDTO exportPreferences(Long accountId) {

        BackupPreferenceDTO dto = new BackupPreferenceDTO();
        dto.setVersion("1.0");
        dto.setExportDate(LocalDateTime.now());

        // ================= ACCOUNT =================
        Account account = repository.findAccount(accountId);

        List<UserAccountProfile> profiles = repository.findProfiles(accountId);

        List<Long> profileIds = profiles.stream()
                .map(UserAccountProfile::getIdProfile)
                .toList();

        List<CreditCard> cards = repository.findCreditCards(profileIds);

        List<CheckBook> checks = repository.findCheckBooks(profileIds);

        Map<Long, List<CreditCard>> cardsByProfile =
                cards.stream().collect(Collectors.groupingBy(CreditCard::getIdProfile));

        Map<Long, List<CheckBook>> checksByProfile =
                checks.stream().collect(Collectors.groupingBy(CheckBook::getIdProfile));

        for (UserAccountProfile profile : profiles) {
            profile.setCreditCards(cardsByProfile.getOrDefault(profile.getIdProfile(), List.of()));
            profile.setCheckBooks(checksByProfile.getOrDefault(profile.getIdProfile(), List.of()));
        }

        account.setProfiles(profiles);
        dto.setAccount(account);

        // ================= TYPES =================
        dto.setOperationTypes(repository.findOperationTypes(accountId));

        // ================= MODES =================
        List<OperationMode> modes = repository.findOperationModes(accountId);

        List<Long> modeIds = modes.stream()
                .map(OperationMode::getIdOperationMode)
                .toList();

        List<ModeKey> keys = repository.findModeKeys(modeIds);

        Map<Long, List<ModeKey>> keysByMode =
                keys.stream().collect(Collectors.groupingBy(ModeKey::getIdMode));

        for (OperationMode mode : modes) {
            mode.setKeys(keysByMode.getOrDefault(mode.getIdOperationMode(), List.of()));
        }

        dto.setOperationModes(modes);

        // ================= CATEGORIES =================
        List<Category> categories = repository.findCategories(accountId);

        List<String> categoryIds = categories.stream()
                .map(Category::getIdCategory)
                .toList();

        List<SubCategory> subs = repository.findSubCategories(categoryIds);

        List<String> subIds = subs.stream()
                .map(SubCategory::getIdSubCategory)
                .toList();

        List<SubCategoryKey> subKeys = repository.findSubCategoryKeys(subIds);

        Map<String, List<SubCategoryKey>> keysBySub =
                subKeys.stream().collect(Collectors.groupingBy(k -> String.valueOf(k.getIdSubCategory())));

        for (SubCategory sub : subs) {
            sub.setKeys(keysBySub.getOrDefault(sub.getIdSubCategory(), List.of()));
        }

        Map<String, List<SubCategory>> subsByCategory =
                subs.stream().collect(Collectors.groupingBy(SubCategory::getIdCategory));

        for (Category cat : categories) {
            cat.setSubCategories(subsByCategory.getOrDefault(cat.getIdCategory(), List.of()));
        }

        dto.setCategories(categories);

        return dto;
    }

    @Override
    public BackupOperationDTO exportOperations(long accountId, long year) {

        List<Operation> operations = repository.findOperationsByAccountAndYear(accountId, year);
        Balance balance = repository.findBalanceByAccountAndYear(accountId, year);

        BackupOperationDTO dto = new BackupOperationDTO();
        dto.setVersion("1.0");
        dto.setExportDate(LocalDateTime.now());
        dto.setAccountId((int) accountId);
        dto.setYear((int) year);
        dto.setOperations(operations);
        dto.setBalance(balance);

        return dto;
    }
}