package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.repository.extractor.UserResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositorySql implements UserRepository{

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        System.out.println("readByPseudoAndPassword: "+pseudo+"  "+password);
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
}
