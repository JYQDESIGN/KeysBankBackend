package com.jyqdesign.keysbankbackend.repository.rowMapper;

import com.jyqdesign.keysbankbackend.entity.Operation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationRowMapper implements RowMapper<Operation> {

    @Override
    public Operation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Operation op = new Operation();
        op.setIdOp(rs.getLong("id_op"));
        op.setIdAccount(rs.getLong("id_account"));
        op.setCategory(rs.getString("category"));
        op.setSubCategory(rs.getString("sub_category"));
        op.setMode(rs.getString("mode"));
        op.setType(rs.getString("type"));
        op.setStatus(rs.getString("status"));
        op.setDescription(rs.getString("description"));
        op.setComment(rs.getString("comment"));
        // convertir DATETIME2 → String
        op.setDate(rs.getTimestamp("date").toLocalDateTime().toString());
        op.setValue(rs.getLong("value"));
        return op;
    }
}