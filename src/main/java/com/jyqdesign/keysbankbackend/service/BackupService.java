package com.jyqdesign.keysbankbackend.service;

import java.io.File;
import java.io.IOException;

public interface BackupService {

    File generateBackup(String databaseName);

    void restoreBackup(String databaseName, File backupFile) throws IOException;
}