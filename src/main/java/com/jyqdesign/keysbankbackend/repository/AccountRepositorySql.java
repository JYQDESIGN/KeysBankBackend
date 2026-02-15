package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.repository.extractor.AccountResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositorySql implements AccountRepository{

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountRepositorySql(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void createAccount(Account account) {

    }

    @Override
    public List<Account> readAll() {
        return List.of();
    }

    @Override
    public Account readById(long id) {
        String sql = """
            SELECT
                a.id_account          AS account_id,
                a.name                AS account_name,
                a.bank                AS account_bank,
                a.reference           AS account_reference,
        
                p.id_profile          AS profile_id,
                p.id_user             AS user_id,
                p.user_role           AS profile_role,
        
                u.pseudo              AS user_pseudo,
                u.first_name          AS user_first_name,
                u.last_name           AS user_last_name,
                u.email               AS user_email,
                u.password            AS user_password,
                u.last_connection     AS user_last_connection,
        
                cc.id_credit_card     AS credit_card_id,
                cc.reference          AS credit_card_reference,
                cc.active             AS credit_card_active,
        
                cb.id_check_book      AS check_book_id,
                cb.first_check_number AS check_book_first_check_number,
                cb.check_range        AS check_book_range,
                cb.active             AS check_book_active
        
            FROM [ACCOUNT] a
            LEFT JOIN ACCOUNT_USER_PROFILE p
                   ON p.id_account = a.id_account
            LEFT JOIN [USER] u
                   ON u.id_user = p.id_user
            LEFT JOIN CREDIT_CARD cc
                   ON cc.id_account_user_profile = p.id_profile
            LEFT JOIN CHECK_BOOK cb
                   ON cb.id_account_user_profile = p.id_profile
            WHERE a.id_account = :id;
        """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        List<Account> accounts = namedParameterJdbcTemplate
                .query(sql, params, new AccountResultSetExtractor());

        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(long id) {

    }
}
