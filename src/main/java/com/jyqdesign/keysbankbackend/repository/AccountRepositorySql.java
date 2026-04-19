package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.repository.extractor.AccountResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AccountRepositorySql implements AccountRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountRepositorySql(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Account createAccount(Account account) {

        String sql = """
                    INSERT INTO ACCOUNT (
                        name, bank, reference, current_solde, last_update,
                        since_year, initial_balance,
                        csv_import_folder, csv_row_ignored, csv_row_date, csv_row_solde,
                        csv_column_number, csv_column_date, csv_date_format,
                        csv_column_description, csv_column_credit, csv_column_debit, csv_column_value
                    )
                    OUTPUT INSERTED.id_account
                    VALUES (
                        :name, :bank, :reference, :currentSolde, :lastUpdate,
                        :sinceYear, :initialBalance,
                        :csvImportFolder, :csvRowIgnored, :csvRowDate, :csvRowSolde,
                        :csvColumnNumber, :csvColumnDate, :csvDateFormat,
                        :csvColumnDescription, :csvColumnCredit, :csvColumnDebit, :csvColumnValue
                    )
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", account.getName())
                .addValue("bank", account.getBank())
                .addValue("reference", account.getReference())
                .addValue("currentSolde", account.getCurrentSolde())
                .addValue("lastUpdate", account.getLastUpdate())
                .addValue("sinceYear", account.getSinceYear())
                .addValue("initialBalance", account.getInitialBalance())
                .addValue("csvImportFolder", account.getCsvImportFolder())
                .addValue("csvRowIgnored", account.getCsvRowIgnored())
                .addValue("csvRowDate", account.getCsvRowDate())
                .addValue("csvRowSolde", account.getCsvRowSolde())
                .addValue("csvColumnNumber", account.getCsvColumnNumber())
                .addValue("csvColumnDate", account.getCsvColumnDate())
                .addValue("csvDateFormat", account.getCsvDateFormat())
                .addValue("csvColumnDescription", account.getCsvColumnDescription())
                .addValue("csvColumnCredit", account.getCsvColumnCredit())
                .addValue("csvColumnDebit", account.getCsvColumnDebit())
                .addValue("csvColumnValue", account.getCsvColumnValue());

        Long generatedId = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                Long.class
        );

        account.setIdAccount(generatedId);

        return account;
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
                        a.current_solde       AS account_current_solde,
                        a.initial_balance     AS account_initial_balance,
                        a.last_update         AS account_last_update,
                        a.since_year          AS account_since_year,
                        a.csv_import_folder,
                        a.csv_row_ignored,
                        a.csv_row_date,
                        a.csv_row_solde,
                        a.csv_column_number,
                        a.csv_column_date,
                        a.csv_date_format,
                        a.csv_column_description,
                        a.csv_column_credit,
                        a.csv_column_debit,
                        a.csv_column_value,
                
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
    public boolean deleteAccount(long id) {
        String sql = """
                    DELETE FROM ACCOUNT
                    WHERE id_account = :idAccount
                """;

        Map<String, Object> params = Map.of("idAccount", id);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);

        return rowsAffected > 0;
    }

    @Override
    public Account updateAccountById(long id, Account updatedAccount) {

        String sql = """
                    UPDATE ACCOUNT SET
                        name = :name,
                        bank = :bank,
                        reference = :reference,
                        current_solde = :currentSolde,
                        last_update = :lastUpdate,
                        since_year = :sinceYear,
                        initial_balance = :initialBalance,
                        csv_import_folder = :csvImportFolder,
                        csv_row_ignored = :csvRowIgnored,
                        csv_row_date = :csvRowDate,
                        csv_row_solde = :csvRowSolde,
                        csv_column_number = :csvColumnNumber,
                        csv_column_date = :csvColumnDate,
                        csv_date_format = :csvDateFormat,
                        csv_column_description = :csvColumnDescription,
                        csv_column_credit = :csvColumnCredit,
                        csv_column_debit = :csvColumnDebit,
                        csv_column_value = :csvColumnValue
                    WHERE id_account = :id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", updatedAccount.getName())
                .addValue("bank", updatedAccount.getBank())
                .addValue("reference", updatedAccount.getReference())
                .addValue("currentSolde", updatedAccount.getCurrentSolde())
                .addValue("lastUpdate", updatedAccount.getLastUpdate())
                .addValue("sinceYear", updatedAccount.getSinceYear())
                .addValue("initialBalance", updatedAccount.getInitialBalance())
                .addValue("csvImportFolder", updatedAccount.getCsvImportFolder())
                .addValue("csvRowIgnored", updatedAccount.getCsvRowIgnored())
                .addValue("csvRowDate", updatedAccount.getCsvRowDate())
                .addValue("csvRowSolde", updatedAccount.getCsvRowSolde())
                .addValue("csvColumnNumber", updatedAccount.getCsvColumnNumber())
                .addValue("csvColumnDate", updatedAccount.getCsvColumnDate())
                .addValue("csvDateFormat", updatedAccount.getCsvDateFormat())
                .addValue("csvColumnDescription", updatedAccount.getCsvColumnDescription())
                .addValue("csvColumnCredit", updatedAccount.getCsvColumnCredit())
                .addValue("csvColumnDebit", updatedAccount.getCsvColumnDebit())
                .addValue("csvColumnValue", updatedAccount.getCsvColumnValue());

        int rows = namedParameterJdbcTemplate.update(sql, params);

        if (rows == 0) {
            return null; // ou throw exception
        }

        updatedAccount.setIdAccount(id);
        return updatedAccount;
    }
}
