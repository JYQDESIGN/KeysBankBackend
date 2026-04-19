package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.entity.dto.BackupOperationDTO;
import com.jyqdesign.keysbankbackend.entity.dto.BackupPreferenceDTO;
import com.jyqdesign.keysbankbackend.service.AccountService;
import com.jyqdesign.keysbankbackend.service.BackupService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

    private final BackupService service;
    private final AccountService accountService;

    public BackupController(BackupService service, AccountService accountService) {
        this.service = service;
        this.accountService = accountService;
    }

    @GetMapping("/preferences/{accountId}")
    public ResponseEntity<byte[]> export(@PathVariable Long accountId) throws Exception {

        BackupPreferenceDTO dto = service.exportPreferences(accountId);
        byte[] json = new ObjectMapper().writeValueAsBytes(dto);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss"));
        String filename = "backup_preferences_"+ dto.getAccount().getName() +"__"+ timestamp + ".json";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION) // ⚡ très important
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }

    @GetMapping("/operations/{accountId}")
    public ResponseEntity<byte[]> export(@PathVariable Long accountId, @RequestParam long year) throws Exception {

        //get account name
        Account account = this.accountService.readById(accountId);

        BackupOperationDTO dto = service.exportOperations(accountId, year);
        byte[] json = new ObjectMapper().writeValueAsBytes(dto);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss"));
        String filename = "backup_operations_" + account.getName() + "_" + dto.getYear() + "__" + timestamp + ".json";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION) // ⚡ très important
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }

    @GetMapping("/full/{accountId}")
    public ResponseEntity<byte[]> fullExport(@PathVariable Long accountId) throws Exception {

        //get account name
        Account account = this.accountService.readById(accountId);
        //current year
        int currentYear = LocalDate.now().getYear();
        ArrayList<BackupOperationDTO> fullOps = new ArrayList<>();
        for(long y = account.getSinceYear() ; y <= currentYear ; y++) {
            fullOps.add(service.exportOperations(accountId, y));
        }
        byte[] json = new ObjectMapper().writeValueAsBytes(fullOps);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss"));
        String filename = "backup_full_operations_" + account.getName() + "_from" +account.getSinceYear()+ "to" + currentYear + "__" + timestamp + ".json";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION) // ⚡ très important
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }
}