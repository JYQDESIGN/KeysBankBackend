package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.dto.BackupOperationDTO;
import com.jyqdesign.keysbankbackend.entity.dto.BackupPreferenceDTO;
import com.jyqdesign.keysbankbackend.service.BackupService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

    private final BackupService service;

    public BackupController(BackupService service) {
        this.service = service;
    }

    @GetMapping("/preferences/{accountId}")
    public ResponseEntity<byte[]> export(@PathVariable Long accountId) throws Exception {

        BackupPreferenceDTO dto = service.exportPreferences(accountId);
        byte[] json = new ObjectMapper().writeValueAsBytes(dto);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));
        String filename = "backup_preferences_"+ dto.getAccount().getName() +"_"+ timestamp + ".json";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION) // ⚡ très important
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }

    @GetMapping("/operations/{accountId}")
    public ResponseEntity<byte[]> export(@PathVariable Long accountId, @RequestParam long year) throws Exception {

        BackupOperationDTO dto = service.exportOperations(accountId, year);
        byte[] json = new ObjectMapper().writeValueAsBytes(dto);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));
        String filename = "backup_operations_" + dto.getAccountId() + "_" + dto.getYear() + "_" + timestamp + ".json";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION) // ⚡ très important
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }
}