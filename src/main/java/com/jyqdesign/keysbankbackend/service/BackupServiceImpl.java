package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.repository.BackupRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BackupServiceImpl implements BackupService {

    private final BackupRepository backupRepository;

    public BackupServiceImpl(BackupRepository backupRepository) {
        this.backupRepository = backupRepository;
    }

    @Override
    public File generateBackup(String databaseName) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String path = "C:/backups/" + databaseName + "_" + timestamp + ".bak";

        backupRepository.backupDatabase(databaseName, path);

        return new File(path);
    }

    @Override
    public void restoreBackup(String databaseName, File backupFile) {

        backupRepository.restoreDatabase(databaseName, backupFile.getAbsolutePath());
    }
}