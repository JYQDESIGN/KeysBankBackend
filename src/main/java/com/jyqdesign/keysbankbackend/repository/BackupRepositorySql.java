package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BackupRepositorySql implements BackupRepository {

    private final JdbcTemplate jdbcTemplate;

    public BackupRepositorySql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ================= ACCOUNT =================

    @Override
    public Account findAccount(Long accountId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM ACCOUNT WHERE id_account = ?",
                new BeanPropertyRowMapper<>(Account.class),
                accountId
        );
    }

    // ================= PROFILES =================

    @Override
    public List<UserAccountProfile> findProfiles(Long accountId) {
        return jdbcTemplate.query(
                "SELECT id_profile AS idProfile, id_account AS idAccount, user_role AS role " +
                        "FROM ACCOUNT_USER_PROFILE WHERE id_account = ?",
                new BeanPropertyRowMapper<>(UserAccountProfile.class),
                accountId
        );
    }

    // ================= CREDIT CARD =================

    @Override
    public List<CreditCard> findCreditCards(List<Long> profileIds) {

        if (profileIds == null || profileIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = profileIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT id_credit_card AS idCreditCard, " +
                "id_account_user_profile AS idProfile, reference, active " +
                "FROM CREDIT_CARD WHERE id_account_user_profile IN (" + inSql + ")";

        return jdbcTemplate.query(
                sql,
                profileIds.toArray(),
                new BeanPropertyRowMapper<>(CreditCard.class)
        );
    }

    // ================= CHECK BOOK =================

    @Override
    public List<CheckBook> findCheckBooks(List<Long> profileIds) {

        if (profileIds == null || profileIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = profileIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT id_check_book AS idCheckBook, " +
                "id_account_user_profile AS idProfile, " +
                "first_check_number AS firstCheckNumber, " +
                "check_range AS range, active " +
                "FROM CHECK_BOOK WHERE id_account_user_profile IN (" + inSql + ")";

        return jdbcTemplate.query(
                sql,
                profileIds.toArray(),
                new BeanPropertyRowMapper<>(CheckBook.class)
        );
    }

    // ================= OPERATION TYPE =================

    @Override
    public List<OperationType> findOperationTypes(Long accountId) {
        return jdbcTemplate.query(
                "SELECT id_op_type AS idOperationType, id_account AS idAccount, " +
                        "op_type_label AS label, op_type_value AS value, " +
                        "op_type_color AS color, op_type_icon AS icon " +
                        "FROM OPERATION_TYPE WHERE id_account = ?",
                new BeanPropertyRowMapper<>(OperationType.class),
                accountId
        );
    }

    // ================= OPERATION MODE =================

    @Override
    public List<OperationMode> findOperationModes(Long accountId) {
        return jdbcTemplate.query(
                "SELECT id_op_mode AS idOperationMode, id_account AS idAccount, " +
                        "op_mode_label AS label, op_mode_value AS value, " +
                        "op_mode_color AS color, op_mode_icon AS icon " +
                        "FROM OPERATION_MODE WHERE id_account = ?",
                new BeanPropertyRowMapper<>(OperationMode.class),
                accountId
        );
    }

    // ================= MODE KEY =================

    @Override
    public List<ModeKey> findModeKeys(List<Long> modeIds) {

        if (modeIds == null || modeIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = modeIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT id_op_mode AS idMode, id_key AS idKey, key_label AS [key] " +
                "FROM MODE_KEY WHERE id_op_mode IN (" + inSql + ")";

        return jdbcTemplate.query(
                sql,
                modeIds.toArray(),
                new BeanPropertyRowMapper<>(ModeKey.class)
        );
    }

    // ================= CATEGORY =================

    @Override
    public List<Category> findCategories(Long accountId) {
        return jdbcTemplate.query(
                "SELECT id_op_category AS idCategory, " +
                        "op_category_label AS label, " +
                        "op_category_type AS type, " +
                        "op_category_color AS color " +
                        "FROM OPERATION_CATEGORY WHERE id_account = ?",
                new BeanPropertyRowMapper<>(Category.class),
                accountId
        );
    }

    // ================= SUB CATEGORY =================

    @Override
    public List<SubCategory> findSubCategories(List<String> categoryIds) {

        if (categoryIds == null || categoryIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = categoryIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT id_op_sub_category AS idSubCategory, " +
                "id_op_category AS idCategory, " +
                "op_sub_category_label AS label, " +
                "op_sub_category_type AS type, " +
                "op_sub_category_color AS color " +
                "FROM OPERATION_SUB_CATEGORY WHERE id_op_category IN (" + inSql + ")";

        return jdbcTemplate.query(
                sql,
                categoryIds.toArray(),
                new BeanPropertyRowMapper<>(SubCategory.class)
        );
    }

    // ================= SUB CATEGORY KEY =================

    @Override
    public List<SubCategoryKey> findSubCategoryKeys(List<String> subCategoryIds) {

        if (subCategoryIds == null || subCategoryIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = subCategoryIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT id_op_sub_category AS idSubCategory, " +
                "id_key AS idKey, key_label AS [key] " +
                "FROM SUB_CATEGORY_KEY WHERE id_op_sub_category IN (" + inSql + ")";

        return jdbcTemplate.query(
                sql,
                subCategoryIds.toArray(),
                new BeanPropertyRowMapper<>(SubCategoryKey.class)
        );
    }

    // ======================= OPERATION ======================

    @Override
    public List<Operation> findOperationsByAccountAndYear(long accountId, long year) {
        String sql = "SELECT id_op AS idOp, id_account AS idAccount, category, sub_category, " +
                "mode, [type], [status], [description], comment, [date], [value] " +
                "FROM OPERATION " +
                "WHERE id_account = ? AND YEAR([date]) = ?";
        return jdbcTemplate.query(sql,
                new Object[]{accountId, year},
                BeanPropertyRowMapper.newInstance(Operation.class));
    }

    @Override
    public Balance findBalanceByAccountAndYear(long accountId, long year) {
        String sql = """
                SELECT 
                    id_balance_sheet AS idBalanceSheet,
                    id_account AS idAccount,
                    [year],
                    ending_solde AS endingSolde,
                    starting_balance AS startingBalance,
                    operation_count AS operationCount,
                    cumul_credit AS cumulCredit,
                    cumul_debit AS cumulDebit,
                    cumul_none AS cumulNone,
                    cumul_saving AS cumulSaving,
                    cumul_survival AS cumulSurvival,
                    cumul_cultural AS cumulCultural,
                    cumul_optional AS cumulOptional,
                    cumul_extra AS cumulExtra,
                    cumul_income AS cumulIncome
                FROM 
                    BALANCE_SHEET
                WHERE 
                    id_account = ? AND [year] = ?
        """;
        return jdbcTemplate.queryForObject(sql,
                new Object[]{accountId, year},
                BeanPropertyRowMapper.newInstance(Balance.class));
    }
}