package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.CheckBook;
import com.jyqdesign.keysbankbackend.entity.CreditCard;
import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.entity.UserAccountProfile;
import com.jyqdesign.keysbankbackend.repository.extractor.UserResultSetExtractor;
import com.jyqdesign.keysbankbackend.repository.rowMapper.CheckBookRowMapper;
import com.jyqdesign.keysbankbackend.repository.rowMapper.CreditCardRowMapper;
import com.jyqdesign.keysbankbackend.repository.rowMapper.UserAccountProfileRowMapper;
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
public class UserRepositorySql implements UserRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CreditCardRowMapper creditCardRowMapper = new CreditCardRowMapper();
    private final CheckBookRowMapper checkBookRowMapper = new CheckBookRowMapper();

    public UserRepositorySql(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public List<User> readAll() {
        String sql = """
                    SELECT
                        u.id_user            AS user_id,
                        u.pseudo             AS user_pseudo,
                        u.first_name         AS user_first_name,
                        u.last_name          AS user_last_name,
                        u.email              AS user_email,
                        u.password           AS user_password,
                        u.last_connection    AS user_last_connection,
                
                        a.id_account         AS account_id,
                        a.name               AS account_name,
                        a.bank               AS account_bank,
                        a.reference          AS account_reference,
                
                        p.user_role          AS account_role,
                        p.id_profile         AS profile_id
                
                    FROM [USER] u
                    LEFT JOIN ACCOUNT_USER_PROFILE p
                           ON p.id_user = u.id_user
                    LEFT JOIN ACCOUNT a
                           ON a.id_account = p.id_account
                    ORDER BY u.id_user;
                """;
        return jdbcTemplate.query(sql, new UserResultSetExtractor());
    }

    @Override
    public User readById(long id) {
        String sql = """
                    SELECT
                        u.id_user            AS user_id,
                        u.pseudo             AS user_pseudo,
                        u.first_name         AS user_first_name,
                        u.last_name          AS user_last_name,
                        u.email              AS user_email,
                        u.password           AS user_password,
                        u.last_connection    AS user_last_connection,
                
                        a.id_account         AS account_id,
                        a.name               AS account_name,
                        a.bank               AS account_bank,
                        a.reference          AS account_reference,
                
                        p.user_role          AS account_role,
                        p.id_profile         AS profile_id
                
                    FROM [USER] u
                    LEFT JOIN ACCOUNT_USER_PROFILE p
                           ON p.id_user = u.id_user
                    LEFT JOIN ACCOUNT a
                           ON a.id_account = p.id_account
                    WHERE u.id_user = :id;
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        List<User> users = namedParameterJdbcTemplate
                .query(sql, params, new UserResultSetExtractor());

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User readByPseudoAndPassword(String pseudo, String password) {
        System.out.println("readByPseudoAndPassword: " + pseudo + "  " + password);
        String sql = """
                    SELECT
                        u.id_user            AS user_id,
                        u.pseudo             AS user_pseudo,
                        u.first_name         AS user_first_name,
                        u.last_name          AS user_last_name,
                        u.email              AS user_email,
                        u.password           AS user_password,
                        u.last_connection    AS user_last_connection,
                
                        a.id_account         AS account_id,
                        a.name               AS account_name,
                        a.bank               AS account_bank,
                        a.reference          AS account_reference,
                
                        p.user_role          AS account_role,
                        p.id_profile         AS profile_id
                
                    FROM [USER] u
                    LEFT JOIN ACCOUNT_USER_PROFILE p
                           ON p.id_user = u.id_user
                    LEFT JOIN ACCOUNT a
                           ON a.id_account = p.id_account
                    WHERE u.pseudo = :pseudo AND u.password = :password;
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("pseudo", pseudo)
                .addValue("password", password);

        List<User> users = namedParameterJdbcTemplate
                .query(sql, params, new UserResultSetExtractor());

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(long id) {

    }

    // =========================================================
    // CREDIT CARD
    // =========================================================

    @Override
    public CreditCard createCard(CreditCard newCard) {

        String sql = """
                    INSERT INTO CREDIT_CARD
                    (id_account_user_profile, reference, active)
                    VALUES (?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setLong(1, newCard.getIdProfile());
            ps.setLong(2, newCard.getReference());
            ps.setBoolean(3, newCard.isActive());

            return ps;

        }, keyHolder);

        Number id = keyHolder.getKey();
        if (id != null) {
            newCard.setIdCreditCard(id.longValue());
        }

        return newCard;
    }

    @Override
    public CreditCard updateCard(long id, boolean active) {

        String sql = """
                    UPDATE CREDIT_CARD
                    SET active = ?
                    WHERE id_credit_card = ?
                """;

        int rows = jdbcTemplate.update(sql, active, id);

        if (rows == 0) {
            return null;
        }
        String selectSql = """
                    SELECT *
                    FROM CREDIT_CARD
                    WHERE id_credit_card = ?
                """;

        return jdbcTemplate.queryForObject(selectSql, creditCardRowMapper, id);
    }

    @Override
    public boolean deleteCard(long id) {

        String sql = """
                    DELETE FROM CREDIT_CARD
                    WHERE id_credit_card = ?
                """;

        return jdbcTemplate.update(sql, id) > 0;
    }


    // =========================================================
    // CHECK BOOK
    // =========================================================

    @Override
    public CheckBook createCheckBook(CheckBook newCheck) {

        String sql = """
                    INSERT INTO CHECK_BOOK
                    (id_account_user_profile, first_check_number, check_range, active)
                    VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setLong(1, newCheck.getIdProfile());
            ps.setLong(2, newCheck.getFirstCheckNumber());
            ps.setLong(3, newCheck.getRange());
            ps.setBoolean(4, newCheck.isActive());

            return ps;

        }, keyHolder);

        Number id = keyHolder.getKey();
        if (id != null) {
            newCheck.setIdCheckBook(id.longValue());
        }

        return newCheck;
    }

    @Override
    public CheckBook updateCheckBox(long id, boolean active) {

        String sql = """
                    UPDATE CHECK_BOOK
                    SET active = ?
                    WHERE id_check_book = ?
                """;

        int rows = jdbcTemplate.update(sql, active, id);

        if (rows == 0) {
            return null;
        }

        String selectSql = """
                    SELECT *
                    FROM CHECK_BOOK
                    WHERE id_check_book = ?
                """;

        return jdbcTemplate.queryForObject(selectSql, checkBookRowMapper, id);
    }

    @Override
    public boolean deleteCheckBook(long id) {

        String sql = """
                    DELETE FROM CHECK_BOOK
                    WHERE id_check_book = ?
                """;

        return jdbcTemplate.update(sql, id) > 0;
    }

    // =========================================================
    // PROFILE
    // =========================================================

    @Override
    public UserAccountProfile updateProfile(long id, UserAccountProfile profile) {

        String updateSql = """
                    UPDATE ACCOUNT_USER_PROFILE
                    SET user_role = ?
                    WHERE id_profile = ?
                """;

        int rows = jdbcTemplate.update(
                updateSql,
                profile.getRole(),
                id
        );

        if (rows == 0) {
            return null;
        }

        String selectSql = """
                    SELECT *
                    FROM ACCOUNT_USER_PROFILE
                    WHERE id_profile = ?
                """;

        UserAccountProfile updatedProfile = jdbcTemplate.queryForObject(selectSql, new UserAccountProfileRowMapper(), id);

        return updatedProfile;
    }

}
