package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.Operation;
import com.jyqdesign.keysbankbackend.entity.SubCategory;
import com.jyqdesign.keysbankbackend.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @GetMapping("/{id}")
    public List<Operation> readOperations(
            @PathVariable long id,
            @RequestParam String start,
            @RequestParam String end) {
        System.out.println("readOperation of "+id);
        return operationService.readOperations(id, convertToTimestamp(start), convertToTimestamp(end));
    }

    private Timestamp convertToTimestamp(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS"));
        return Timestamp.valueOf(localDateTime);
    }

    @PostMapping("")
    public ResponseEntity<Operation> createOperation(
            @RequestBody Operation newOperation) {
        Operation result = operationService.createOperation(newOperation);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public Operation updateOperationById(
            @PathVariable long id,
            @RequestBody Operation updatedOperation) {
        return operationService.updateOperationById(id, updatedOperation);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOperationById(
            @PathVariable long id) {
        return operationService.deleteOperationById(id);
    }
}
