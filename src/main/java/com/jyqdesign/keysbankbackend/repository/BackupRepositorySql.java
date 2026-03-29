package com.jyqdesign.keysbankbackend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BackupRepositorySql implements BackupRepository {

    private final JdbcTemplate jdbcTemplate;

    public BackupRepositorySql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String backupDatabase(String databaseName, String backupPath) {

        String sql = "BACKUP DATABASE " + databaseName +
                " TO DISK = '" + backupPath + "'" +
                " WITH FORMAT, INIT, COMPRESSION";

        jdbcTemplate.execute(sql);

        return backupPath;
    }

    @Override
    public void restoreDatabase(String databaseName, String backupPath) {

        String setSingleUser = "ALTER DATABASE " + databaseName +
                " SET SINGLE_USER WITH ROLLBACK IMMEDIATE";

        String restoreSql = "RESTORE DATABASE " + databaseName +
                " FROM DISK = '" + backupPath + "' WITH REPLACE";

        String setMultiUser = "ALTER DATABASE " + databaseName +
                " SET MULTI_USER";

        jdbcTemplate.execute(setSingleUser);
        jdbcTemplate.execute(restoreSql);
        jdbcTemplate.execute(setMultiUser);
    }
}