package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Operation;
import com.jyqdesign.keysbankbackend.repository.rowMapper.OperationRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OperationRepositorySql implements OperationRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OperationRepositorySql(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // Formatter pour convertir "18/02/2026"
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private Timestamp convertToTimestamp(String date) {
        LocalDate localDate = LocalDate.parse(date, FORMATTER);
        return Timestamp.valueOf(localDate.atStartOfDay());
    }


    @Override
    public int createOperations(List<Operation> operations) {
        String sql = """
                    INSERT INTO OPERATION
                    (
                        id_account, 
                        category, 
                        sub_category, 
                        mode, 
                        type, 
                        status, 
                        description, 
                        comment, 
                        date, 
                        value
                    )
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        int[][] rows = jdbcTemplate.batchUpdate(
                sql,
                operations,
                operations.size(),
                (ps, operation) -> {

                    ps.setLong(1, operation.getIdAccount());
                    ps.setString(2, operation.getCategory());
                    ps.setString(3, operation.getSubCategory());
                    ps.setString(4, operation.getMode().name());   // Mode enum → String
                    ps.setString(5, operation.getType().name());
                    ps.setString(6, operation.getStatus().name());
                    ps.setString(7, operation.getDescription());
                    ps.setString(8, operation.getComment());
                    LocalDateTime date = LocalDateTime.parse(operation.getDate());
                    ps.setTimestamp(9, Timestamp.valueOf(date));
                    ps.setLong(10, operation.getValue());
                }
        );
        int total = 0;
        for (int[] batch : rows) {
            for (int count : batch) {
                total += count;
            }
        }
        System.out.println("Rows created: " + total);
        return total;
    }

    @Override
    public int updateOperations(List<Operation> operations) {

        String sql = """
                    UPDATE OPERATION
                    SET
                        id_account   = ?,
                        category     = ?,
                        sub_category = ?,
                        mode         = ?,
                        type         = ?,
                        status       = ?,
                        description  = ?,
                        comment      = ?,
                        date         = ?,
                        value        = ?
                    WHERE id_op = ?
                """;

        int[][] rows = jdbcTemplate.batchUpdate(
                sql,
                operations,
                operations.size(),
                (ps, operation) -> {

                    ps.setLong(1, operation.getIdAccount());
                    ps.setString(2, operation.getCategory());
                    ps.setString(3, operation.getSubCategory());
                    ps.setString(4, operation.getMode().name());   // Mode enum → String
                    ps.setString(5, operation.getType().name());
                    ps.setString(6, operation.getStatus().name());
                    ps.setString(7, operation.getDescription());
                    ps.setString(8, operation.getComment());
                    LocalDateTime date = LocalDateTime.parse(operation.getDate());
                    ps.setTimestamp(9, Timestamp.valueOf(date));
                    ps.setLong(10, operation.getValue());

                    // IMPORTANT : id pour le WHERE
                    ps.setLong(11, operation.getIdOp());
                }
        );
        int total = 0;
        for (int[] batch : rows) {
            for (int count : batch) {
                total += count;
            }
        }
        System.out.println("Rows updated: " + total);
        return total;
    }

    @Override
    public Operation createOperation(Operation operation) {

        String sql = """
                    INSERT INTO OPERATION
                    (
                        id_account,
                        category,
                        sub_category,
                        mode,
                        type,
                        status,
                        description,
                        comment,
                        date,
                        value
                    )
                    OUTPUT INSERTED.id
                    VALUES
                    (
                        :idAccount,
                        :category,
                        :subCategory,
                        :mode,
                        :type,
                        :status,
                        :description,
                        :comment,
                        :date,
                        :value
                    )
                """;

        Map<String, Object> params = new HashMap<>();

        params.put("idAccount", operation.getIdAccount());
        params.put("category", operation.getCategory());
        params.put("subCategory", operation.getSubCategory());
        params.put("mode", operation.getMode());
        params.put("type", operation.getType());
        params.put("status", operation.getStatus());
        params.put("description", operation.getDescription());
        params.put("comment", operation.getComment());
        LocalDateTime date = LocalDateTime.parse(operation.getDate());
        params.put("date", date);
        params.put("value", operation.getValue());

        Long generatedId = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                Long.class
        );
        if (generatedId != null) {
            operation.setIdOp(generatedId);
            return operation;
        }
        return null;
    }

    @Override
    public List<Operation> readOperations(long idAccount, Timestamp start, Timestamp end) {
        System.out.println("REPOSITORY readOperation of " + idAccount);
        System.out.println("REPOSITORY readOperation of " + start);
        System.out.println("REPOSITORY readOperation of " + end);
        String sql = """
                    SELECT *
                    FROM OPERATION
                    WHERE id_account = :idAccount
                    AND date BETWEEN :startDate AND :endDate
                    ORDER BY date DESC
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("idAccount", idAccount)
                .addValue("startDate", start)
                .addValue("endDate", end);

        return namedParameterJdbcTemplate.query(sql, params, new OperationRowMapper());
    }

    @Override
    public Operation updateOperationById(long id, Operation updatedOperation) {

        String sql = """
                    UPDATE OPERATION
                    SET
                        id_account   = :idAccount,
                        category     = :category,
                        sub_category = :subCategory,
                        mode         = :mode,
                        type         = :type,
                        status       = :status,
                        description  = :description,
                        comment      = :comment,
                        date         = :date,
                        value        = :value
                    OUTPUT INSERTED.id_op
                    WHERE id_op = :id
                """;

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("idAccount", updatedOperation.getIdAccount());
        params.put("category", updatedOperation.getCategory());
        params.put("subCategory", updatedOperation.getSubCategory());
        params.put("mode", updatedOperation.getMode());
        params.put("type", updatedOperation.getType());
        params.put("status", updatedOperation.getStatus());
        params.put("description", updatedOperation.getDescription());
        params.put("comment", updatedOperation.getComment());
        LocalDateTime date = LocalDateTime.parse(updatedOperation.getDate());
        params.put("date", date);
        params.put("value", updatedOperation.getValue());

        Long updatedId = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                Long.class
        );

        if (updatedId == null) {
            return null; // ou throw exception
        }

        return updatedOperation;
    }

    @Override
    public boolean deleteOperationById(long id) {
        String sql = """
                    DELETE FROM OPERATION
                    WHERE id = :id
                """;

        Map<String, Object> params = Map.of("id", id);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);

        return rowsAffected == 1;
    }

}
