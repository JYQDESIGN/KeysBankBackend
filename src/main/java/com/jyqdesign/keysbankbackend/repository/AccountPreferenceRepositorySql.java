package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.*;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;
import com.jyqdesign.keysbankbackend.repository.extractor.CategoryResultSetExtractor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AccountPreferenceRepositorySql implements AccountPreferenceRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountPreferenceRepositorySql(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //=========================================================================
    // TYPES
    //=========================================================================

    @Override
    public void createDefaulTypes(long idAccount) {

        String sql = """
            INSERT INTO OPERATION_TYPE
            (id_account, op_type_label, op_type_value, op_type_color, op_type_icon)
            VALUES (?, ?, ?, ?, ?)
        """;

        OperationType.getDefaultOperationTypes().forEach(type -> {

            jdbcTemplate.update(
                    sql,
                    idAccount,
                    type.getLabel(),
                    type.getValue().name(),  // enum → String
                    type.getColor(),
                    type.getIcon()
            );

        });
    }

    @Override
    public List<OperationType> readPreferenceTypes(long idAccount) {
        String sql = """
            SELECT
                id_op_type AS idOperationType,
                id_account AS idAccount,
                op_type_label AS label,
                op_type_value AS value,
                op_type_color AS color,
                op_type_icon AS icon
            FROM OPERATION_TYPE
            WHERE id_account = :idAccount
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idAccount", idAccount);

        return namedParameterJdbcTemplate.query(
                sql,
                map,
                new BeanPropertyRowMapper<>(OperationType.class)
        );
    }

    //=========================================================================
    // MODES
    //=========================================================================

    @Override
    public void createDefaultModes(long idAccount) {
        String sql = """
            INSERT INTO OPERATION_MODE
            (id_account, op_mode_label, op_mode_value, op_mode_color, op_mode_icon)
            VALUES (?, ?, ?, ?, ?)
        """;

        OperationMode.getDefaultOperationModes().forEach(mode -> {

            jdbcTemplate.update(
                    sql,
                    idAccount,
                    mode.getLabel(),
                    mode.getValue().name(),  // enum → String
                    mode.getColor(),
                    mode.getIcon()
            );

        });
    }

    @Override
    public List<OperationMode> readPreferenceModes(long idAccount) {
        String sql = """
            SELECT
                id_op_mode AS idOperationMode,
                id_account AS idAccount,
                op_mode_label AS label,
                op_mode_value AS value,
                op_mode_color AS color,
                op_mode_icon AS icon
            FROM OPERATION_MODE
            WHERE id_account = :idAccount
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idAccount", idAccount);

        return namedParameterJdbcTemplate.query(
                sql,
                map,
                new BeanPropertyRowMapper<>(OperationMode.class)
        );
    }

    //=========================================================================
    // CATEGORIES
    //=========================================================================

    @Override
    public void createDefaultCategories(long idAccount) {

        List<Category> categories = Category.getDefaultCategories();
        for (Category category : categories) {

            // 1. INSERT CATEGORY
            String insertCategorySql = """
                INSERT INTO OPERATION_CATEGORY
                (
                    id_account,
                    op_category_label,
                    op_category_color,
                    op_category_type
                )
                OUTPUT INSERTED.id_op_category
                VALUES
                (
                    :idAccount,
                    :label,
                    :color,
                    :type
                )
            """;

            MapSqlParameterSource catParams = new MapSqlParameterSource();
            catParams.addValue("idAccount", idAccount);
            catParams.addValue("label", category.getLabel());
            catParams.addValue("color", category.getColor());
            catParams.addValue("type", category.getType());

            // récupérer l'id généré par SQL Server
            Long generatedCategoryId = namedParameterJdbcTemplate.queryForObject(insertCategorySql,catParams,Long.class);

            // 2. INSERT SUBCATEGORIES (ignore id JSON et keys)
            if (category.getSubCategories() != null) {

                for (SubCategory subCategory : category.getSubCategories()) {

                    String insertSubCategorySql = """
                        INSERT INTO OPERATION_SUB_CATEGORY
                        (
                            id_op_category,
                            op_sub_category_label,
                            op_sub_category_color,
                            op_sub_category_type
                        )
                        OUTPUT INSERTED.id_op_sub_category
                        VALUES
                        (
                            :idCategory,
                            :label,
                            :color,
                            :type
                        )
                    """;

                    MapSqlParameterSource subParams = new MapSqlParameterSource();
                    subParams.addValue("idCategory", generatedCategoryId);
                    subParams.addValue("label", subCategory.getLabel());
                    subParams.addValue("color", subCategory.getColor());
                    subParams.addValue("type", subCategory.getType());

                    // récupérer l'id généré par SQL Server
                    Long generatedSubCategoryId = namedParameterJdbcTemplate.queryForObject(insertSubCategorySql, subParams, Long.class);

                    if (subCategory.getKeys() != null) {
                        for (SubCategoryKey key : subCategory.getKeys()) {
                            String insertKeySql = """
                                    INSERT INTO SUB_CATEGORY_KEY
                                    (
                                        id_op_sub_category,
                                        key_label
                                    )
                                    OUTPUT INSERTED.id_key
                                    VALUES
                                    (
                                        :idSubCategorie,
                                        :key
                                    )
                                    """;
                            MapSqlParameterSource keyParams = new MapSqlParameterSource();
                            keyParams.addValue("idSubCategorie", generatedSubCategoryId);
                            keyParams.addValue("key", key.getKey());

                            // récupérer l'id généré par SQL Server
                            Long generatedKeyId = namedParameterJdbcTemplate.queryForObject(insertKeySql, keyParams, Long.class);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<Category> readPreferenceCategories(long idAccount) {
        String sql = """
            SELECT
                c.id_op_category AS idCategory,
                c.id_account AS idAccount,
                c.op_category_label AS cLabel,
                c.op_category_color AS cColor,
                c.op_category_type AS cType,
            
                sc.id_op_sub_category AS idSubCategory,
                sc.op_sub_category_label AS scLabel,
                sc.op_sub_category_color AS scColor,
                sc.op_sub_category_type AS scType,
            
                k.id_key AS idKey,
                k.key_label AS kLabel
            FROM OPERATION_CATEGORY c
            LEFT JOIN OPERATION_SUB_CATEGORY sc ON sc.id_op_category = c.id_op_category
            LEFT JOIN SUB_CATEGORY_KEY k ON k.id_op_sub_category = sc.id_op_sub_category
            WHERE c.id_account = :idAccount
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idAccount", idAccount);

        return namedParameterJdbcTemplate.query(
                sql,
                map,
                new CategoryResultSetExtractor()
        );
    }

    //=========================================================================
    // FULL
    //=========================================================================

    @Override
    public AccountPreferencesDTO readAccountPreferencesById(long idAccount) {
        // Récupère la liste des types
        List<OperationType> types = readPreferenceTypes(idAccount);

        // Récupère la liste des modes
        List<OperationMode> modes = readPreferenceModes(idAccount);

        // Récupère la liste des categories/sous-categories/keys
        List<Category> categories = readPreferenceCategories(idAccount);

        // Construit le DTO
        AccountPreferencesDTO dto = new AccountPreferencesDTO(types, modes, categories);

        // Retourne le DTO complet
        return dto;
    }

    @Override
    public OperationMode updatePreferenceModeById(long id, OperationMode updatedMode) {

        String sql = """
            UPDATE OPERATION_MODE
            SET 
                op_mode_label = ?, 
                op_mode_color = ?, 
                op_mode_icon = ?
            WHERE id_op_mode = ?
        """;

        int rows = jdbcTemplate.update(
                sql,
                updatedMode.getLabel(),
                updatedMode.getColor(),
                updatedMode.getIcon(),
                id
        );

        if (rows == 0) {
            return null;
        }
        return updatedMode;
    }

    @Override
    public OperationType updatePreferenceTypeById(long id, OperationType updatedType) {

        String sql = """
            UPDATE OPERATION_TYPE
            SET 
                op_type_label = ?, 
                op_type_color = ?, 
                op_type_icon = ?
            WHERE id_op_type = ?
        """;

        int rows = jdbcTemplate.update(
                sql,
                updatedType.getLabel(),
                updatedType.getColor(),
                updatedType.getIcon(),
                id
        );

        if (rows == 0) {
            return null;
        }
        return updatedType;
    }

    //=========================================================================
    // KEY
    //=========================================================================

    @Override
    public SubCategoryKey createPreferenceKey(SubCategoryKey newKey) {
        System.out.println("createPreferenceKey: "+newKey);
        String sql = """
            INSERT INTO SUB_CATEGORY_KEY (id_op_sub_category, key_label)
            VALUES (?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setLong(1, newKey.getIdSubCategory());
            ps.setString(2, newKey.getKey());

            return ps;

        }, keyHolder);

        // Retrieve generated ID
        Number generatedId = keyHolder.getKey();

        if (generatedId != null) {
            newKey.setIdKey(generatedId.longValue());
        }

        return newKey;
    }

    @Override
    public boolean deletePreferenceKeyById(long id) {
        String sql = """
            DELETE FROM SUB_CATEGORY_KEY
            WHERE id_key = ?
        """;
        //Avec JdbcTemplate, une suppression retourne le nombre de lignes affectées.
        int rowsAffected = jdbcTemplate.update(sql, id);

        return rowsAffected > 0;
    }

    //=========================================================================
    // CATEGORY
    //=========================================================================

    @Override
    public Category createPreferenceCategory(Category newCategory) {

        String sql = """
            INSERT INTO OPERATION_CATEGORY
            (op_category_label, op_category_color, op_category_type)
            VALUES (?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, newCategory.getLabel());
            ps.setString(2, newCategory.getColor());
            ps.setString(3, newCategory.getType());

            return ps;

        }, keyHolder);

        Number id = keyHolder.getKey();

        if (id != null) {
            newCategory.setIdCategory(String.valueOf(id.longValue()));
        }

        return newCategory;
    }

    @Override
    public Category updatePreferenceCategoryById(long id, Category updatedCategory) {

        String sql = """
            UPDATE OPERATION_CATEGORY
            SET op_category_label = ?,
                op_category_color = ?,
                op_category_type = ?
            WHERE id_op_category = ?
        """;

        int rows = jdbcTemplate.update(sql,
                updatedCategory.getLabel(),
                updatedCategory.getColor(),
                updatedCategory.getType(),
                id
        );

        if (rows == 0) {
            return null;
        }

        updatedCategory.setIdCategory(String.valueOf(id));

        return updatedCategory;
    }

    @Override
    public boolean deletePreferenceCategoryById(long id) {

        String sql = """
            DELETE FROM OPERATION_CATEGORY
            WHERE id_op_category = ?
        """;

        int rows = jdbcTemplate.update(sql, id);

        // supprimer toutes les sous categories

        // supprimer toutes les clefs des sous categories
        return rows > 0;
    }

    //=========================================================================
    // SUB CATEGORY
    //=========================================================================

    @Override
    public SubCategory createPreferenceSubCategory(SubCategory newSubCategory) {

        String sql = """
            INSERT INTO OPERATION_SUB_CATEGORY
            (id_op_category,
             op_sub_category_label,
             op_sub_category_color,
             op_sub_category_type)
            VALUES (?, ?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setLong(1, Long.parseLong(newSubCategory.getIdCategory())); // id category parent
            ps.setString(2, newSubCategory.getLabel());
            ps.setString(3, newSubCategory.getColor());
            ps.setString(4, newSubCategory.getType());

            return ps;

        }, keyHolder);

        Number id = keyHolder.getKey();

        if (id != null) {
            newSubCategory.setIdSubCategory(String.valueOf(id.longValue()));
        }

        return newSubCategory;
    }

    @Override
    public SubCategory updatePreferenceSubCategoryById(long id, SubCategory updatedSubCategory) {

        String sql = """
            UPDATE OPERATION_SUB_CATEGORY
            SET op_sub_category_label = ?,
                op_sub_category_color = ?,
                op_sub_category_type = ?
            WHERE id_op_sub_category = ?
        """;

        int rows = jdbcTemplate.update(sql,
                updatedSubCategory.getLabel(),
                updatedSubCategory.getColor(),
                updatedSubCategory.getType(),
                id
        );

        if (rows == 0) {
            return null;
        }

        updatedSubCategory.setIdSubCategory(String.valueOf(id));

        return updatedSubCategory;
    }

    @Override
    public boolean deletePreferenceSubCategoryById(long id) {

        String sql = """
            DELETE FROM OPERATION_SUB_CATEGORY
            WHERE id_op_sub_category = ?
        """;

        int rows = jdbcTemplate.update(sql, id);

        // remove all keys
        if (rows > 0) {
            String sql2 = """
                DELETE FROM SUB_CATEGORY_KEY
                WHERE id_op_sub_category = ?
            """;
            jdbcTemplate.update(sql2, id);
        }

        return rows > 0;
    }


}
