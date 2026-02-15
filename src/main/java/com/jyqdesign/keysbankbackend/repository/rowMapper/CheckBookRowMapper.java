package com.jyqdesign.keysbankbackend.repository.rowMapper;

import com.jyqdesign.keysbankbackend.entity.CheckBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBookRowMapper implements RowMapper<CheckBook> {

    @Override
    public CheckBook mapRow(ResultSet rs, int rowNum) throws SQLException {

        CheckBook checkBook = new CheckBook();

        checkBook.setIdCheckBook(rs.getLong("id_check_book"));
        checkBook.setIdProfile(rs.getLong("id_account_user_profile"));
        checkBook.setFirstCheckNumber(rs.getLong("first_check_number"));
        checkBook.setRange(rs.getLong("check_range"));
        checkBook.setActive(rs.getBoolean("active"));

        return checkBook;
    }
}
