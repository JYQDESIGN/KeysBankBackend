package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Balance;
import com.jyqdesign.keysbankbackend.repository.rowMapper.BalanceRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BalanceRepositorySql implements BalanceRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BalanceRepositorySql(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Balance readBalance(long idAccount, long year) {

        String sql = """
                SELECT id_balance_sheet,
                       id_account,
                       [year],
                       starting_balance,
                       cumul_credit,
                       cumul_debit,
                       cumul_none,
                       cumul_saving,
                       cumul_survival,
                       cumul_cultural,
                       cumul_optional,
                       cumul_extra,
                       cumul_income
                FROM BALANCE_SHEET
                WHERE id_account = :idAccount
                AND [year] = :year
                """;

        Map<String, Object> params = Map.of(
                "idAccount", idAccount,
                "year", year
        );

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new BalanceRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Balance updateBalanceById(long idBalanceSheet, Balance updatedBalance) {

        String sql = """
                UPDATE BALANCE_SHEET
                SET starting_balance = :startingBalance,
                    cumul_credit = :cumulCredit,
                    cumul_debit = :cumulDebit,
                    cumul_none = :cumulNone,
                    cumul_saving = :cumulSaving,
                    cumul_survival = :cumulSurvival,
                    cumul_cultural = :cumulCultural,
                    cumul_optional = :cumulOptional,
                    cumul_extra = :cumulExtra,
                    cumul_income = :cumulIncome
                WHERE id_balance_sheet = :idBalanceSheet
                """;

        Map<String, Object> params = new HashMap<>();
        params.put("idAccount", updatedBalance.getIdAccount());
        params.put("idBalanceSheet", idBalanceSheet);
        params.put("startingBalance", updatedBalance.getStartingBalance());
        params.put("cumulCredit", updatedBalance.getCumulCredit());
        params.put("cumulDebit", updatedBalance.getCumulDebit());
        params.put("cumulNone", updatedBalance.getCumulNone());
        params.put("cumulSaving", updatedBalance.getCumulSaving());
        params.put("cumulSurvival", updatedBalance.getCumulSurvival());
        params.put("cumulCultural", updatedBalance.getCumulCultural());
        params.put("cumulOptional", updatedBalance.getCumulOptional());
        params.put("cumulExtra", updatedBalance.getCumulExtra());
        params.put("cumulIncome", updatedBalance.getCumulIncome());

        int rows = namedParameterJdbcTemplate.update(sql, params);

        if (rows == 0) {
            throw new EmptyResultDataAccessException("Balance not found with id " + idBalanceSheet, 1);
        }

        return updatedBalance;
    }

    @Override
    public Balance readFullBalance(long idAccount) {

        String sql = """
        SELECT
            SUM(cumul_credit) AS cumul_credit,
            SUM(cumul_debit) AS cumul_debit,
            SUM(cumul_none) AS cumul_none,
            SUM(cumul_saving) AS cumul_saving,
            SUM(cumul_survival) AS cumul_survival,
            SUM(cumul_cultural) AS cumul_cultural,
            SUM(cumul_optional) AS cumul_optional,
            SUM(cumul_extra) AS cumul_extra,
            SUM(cumul_income) AS cumul_income
        FROM BALANCE_SHEET
        WHERE id_account = :idAccount
        """;

        Map<String, Object> params = Map.of("idAccount", idAccount);

        return namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                (rs, rowNum) -> {
                    Balance balance = new Balance();

                    balance.setIdAccount(idAccount);

                    balance.setCumulCredit(rs.getLong("cumul_credit"));
                    balance.setCumulDebit(rs.getLong("cumul_debit"));
                    balance.setCumulNone(rs.getLong("cumul_none"));
                    balance.setCumulSaving(rs.getLong("cumul_saving"));
                    balance.setCumulSurvival(rs.getLong("cumul_survival"));
                    balance.setCumulCultural(rs.getLong("cumul_cultural"));
                    balance.setCumulOptional(rs.getLong("cumul_optional"));
                    balance.setCumulExtra(rs.getLong("cumul_extra"));
                    balance.setCumulIncome(rs.getLong("cumul_income"));

                    return balance;
                }
        );
    }
}
