package com.jyqdesign.keysbankbackend.repository.extractor;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.entity.UserAccountProfile;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException {

        Map<Long, User> users = new LinkedHashMap<>();

        while (rs.next()) {

            long userId = rs.getLong("user_id");

            //Création ou récupération du User
            User user = users.get(userId);
            if (user == null) {
                user = new User();
                user.setIdUser(userId);
                user.setPseudo(rs.getString("user_pseudo"));
                user.setFirstName(rs.getString("user_first_name"));
                user.setLastName(rs.getString("user_last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getString("user_password"));
                user.setLastConnection(
                        rs.getTimestamp("user_last_connection").toLocalDateTime()
                );
                user.setAccounts(new ArrayList<>());

                users.put(userId, user);
            }

            //Ajout de l’Account s’il n'existe pas
            Long accountId = rs.getObject("account_id", Long.class);
            if (accountId != null) {

                Account account = new Account();
                account.setIdAccount(accountId);
                account.setName(rs.getString("account_name"));
                account.setBank(rs.getString("account_bank"));
                account.setReference(rs.getString("account_reference"));

                UserAccountProfile profile = new UserAccountProfile();
                profile.setIdProfile(rs.getLong("profile_id"));
                profile.setIdAccount(accountId);
                profile.setRole(rs.getString("account_role"));

                if (account.getProfiles()==null) {
                    account.setProfiles(new ArrayList<UserAccountProfile>());
                }
                account.getProfiles().add(profile);
                user.getAccounts().add(account);
            }
        }

        return new ArrayList<>(users.values());
    }
}