package com.jyqdesign.keysbankbackend.repository;

public interface BackupRepository {

    String backupDatabase(String databaseName, String backupPath);
    void restoreDatabase(String databaseName, String backupPath);
}
