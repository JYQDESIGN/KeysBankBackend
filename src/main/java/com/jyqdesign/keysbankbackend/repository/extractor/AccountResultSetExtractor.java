package com.jyqdesign.keysbankbackend.repository.extractor;

import com.jyqdesign.keysbankbackend.entity.*;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class AccountResultSetExtractor implements ResultSetExtractor<List<Account>> {

    @Override
    public List<Account> extractData(ResultSet rs) throws SQLException {

        Map<Long, Account> accountMap = new LinkedHashMap<>();
        Map<Long, UserAccountProfile> profileMap = new LinkedHashMap<>();

        while (rs.next()) {

            long accountId = rs.getLong("account_id");

            // ACCOUNT
            Account account = accountMap.get(accountId);

            if (account == null) {
                account = new Account();
                account.setIdAccount(accountId);
                account.setName(rs.getString("account_name"));
                account.setBank(rs.getString("account_bank"));
                account.setReference(rs.getString("account_reference"));

                accountMap.put(accountId, account);
            }

            // PROFILE
            long profileId = rs.getLong("profile_id");

            if (!rs.wasNull()) {

                UserAccountProfile profile = profileMap.get(profileId);

                if (profile == null) {

                    profile = new UserAccountProfile();
                    profile.setIdProfile(profileId);
                    profile.setRole(rs.getString("profile_role"));

                    // USER
                    long userId = rs.getLong("user_id");

                    if (!rs.wasNull()) {

                        User user = new User();
                        user.setIdUser(userId);
                        user.setPseudo(rs.getString("user_pseudo"));
                        user.setFirstName(rs.getString("user_first_name"));
                        user.setLastName(rs.getString("user_last_name"));
                        user.setEmail(rs.getString("user_email"));
                        user.setPassword(rs.getString("user_password"));
                        user.setLastConnection(rs.getTimestamp("user_last_connection").toLocalDateTime());

                        profile.setUser(user);
                    }

                    profileMap.put(profileId, profile);
                    if (account.getProfiles() == null) {
                        account.setProfiles(new ArrayList<UserAccountProfile>());
                    }
                    account.getProfiles().add(profile);
                }

                // CREDIT CARD
                long creditCardId = rs.getLong("credit_card_id");

                if (!rs.wasNull()) {

                    if (profile.getCreditCards() == null) {
                        profile.setCreditCards(new ArrayList<CreditCard>());
                    }
                    boolean exists = profile.getCreditCards()
                            .stream()
                            .anyMatch(cc -> cc.getIdCreditCard() == creditCardId);

                    if (!exists) {

                        CreditCard cc = new CreditCard();
                        cc.setIdCreditCard(creditCardId);
                        cc.setReference(rs.getLong("credit_card_reference"));
                        cc.setActive(rs.getBoolean("credit_card_active"));

                        profile.getCreditCards().add(cc);
                    }
                }

                // CHECK BOOK
                long checkBookId = rs.getLong("check_book_id");

                if (!rs.wasNull()) {

                    if (profile.getCheckBooks() == null) {
                        profile.setCheckBooks(new ArrayList<CheckBook>());
                    }
                    boolean exists = profile.getCheckBooks()
                            .stream()
                            .anyMatch(cb -> cb.getIdCheckBook() == checkBookId);

                    if (!exists) {

                        CheckBook cb = new CheckBook();
                        cb.setIdCheckBook(checkBookId);
                        cb.setFirstCheckNumber(rs.getLong("check_book_first_check_number"));
                        cb.setRange(rs.getLong("check_book_range"));
                        cb.setActive(rs.getBoolean("check_book_active"));

                        profile.getCheckBooks().add(cb);
                    }
                }
            }
        }

        return new ArrayList<>(accountMap.values());
    }
}

