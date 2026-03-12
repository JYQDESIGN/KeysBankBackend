package com.jyqdesign.keysbankbackend.repository.rowMapper;

import com.jyqdesign.keysbankbackend.entity.Operation;
import com.jyqdesign.keysbankbackend.entity.enums.OpMode;
import com.jyqdesign.keysbankbackend.entity.enums.OpStatus;
import com.jyqdesign.keysbankbackend.entity.enums.OpType;
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
        op.setMode(OpMode.valueOf(rs.getString("mode").trim().toUpperCase()));
        op.setType(OpType.valueOf(rs.getString("type").trim().toUpperCase()));
        op.setStatus(OpStatus.valueOf(rs.getString("status").trim().toUpperCase()));
        op.setDescription(rs.getString("description"));
        op.setComment(rs.getString("comment"));
        // convertir DATETIME2 → String
        op.setDate(rs.getTimestamp("date").toLocalDateTime().toString());
        op.setValue(rs.getLong("value"));
        return op;
    }
}