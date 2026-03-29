package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.service.BackupService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

    private final BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadBackup() throws FileNotFoundException {
        System.out.println("BackupController::downloadBackup");
        File file = backupService.generateBackup("db_keys_bank");

        InputStreamResource resource =
                new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/restore")
    public ResponseEntity<String> restoreBackup(@RequestParam("file") MultipartFile file) throws IOException {

        String path = "C:/backups/" + file.getOriginalFilename();

        File backupFile = new File(path);
        file.transferTo(backupFile);

        backupService.restoreBackup("db_keys_bank", backupFile);

        return ResponseEntity.ok("Database restored successfully");
    }
}