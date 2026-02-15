package com.jyqdesign.keysbankbackend.repository.rowMapper;

import com.jyqdesign.keysbankbackend.entity.UserAccountProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountProfileRowMapper implements RowMapper<UserAccountProfile> {

    @Override
    public UserAccountProfile mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserAccountProfile profile = new UserAccountProfile();

        profile.setRole(rs.getString("user_role"));

        return profile;
    }
}
