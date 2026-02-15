package com.jyqdesign.keysbankbackend.repository.rowMapper;

import com.jyqdesign.keysbankbackend.entity.CreditCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardRowMapper implements RowMapper<CreditCard> {

    @Override
    public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {

        CreditCard card = new CreditCard();

        card.setIdCreditCard(rs.getLong("id_credit_card"));
        card.setIdProfile(rs.getLong("id_account_user_profile"));
        card.setReference(rs.getLong("reference"));
        card.setActive(rs.getBoolean("active"));

        return card;
    }
}
