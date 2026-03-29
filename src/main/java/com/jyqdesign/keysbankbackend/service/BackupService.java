package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.dto.BackupOperationDTO;
import com.jyqdesign.keysbankbackend.entity.dto.BackupPreferenceDTO;

import java.io.File;
import java.io.IOException;

public interface BackupService {

    BackupPreferenceDTO exportPreferences(Long accountId);

    BackupOperationDTO exportOperations(long accountId, long year);

    //File generateBackup(String databaseName);

    //void restoreBackup(String databaseName, File backupFile) throws IOException;
}