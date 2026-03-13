package com.jyqdesign.keysbankbackend.repository.rowMapper;

import com.jyqdesign.keysbankbackend.entity.Balance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceRowMapper implements RowMapper<Balance> {

    @Override
    public Balance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Balance balance = new Balance();

        balance.setIdBalanceSheet(rs.getLong("id_balance_sheet"));
        balance.setIdAccount(rs.getLong("id_account"));
        balance.setYear(rs.getLong("year"));
        balance.setStartingBalance(rs.getLong("starting_balance"));

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
}