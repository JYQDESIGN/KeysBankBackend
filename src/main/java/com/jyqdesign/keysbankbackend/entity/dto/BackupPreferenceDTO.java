package com.jyqdesign.keysbankbackend.entity.dto;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.entity.Category;
import com.jyqdesign.keysbankbackend.entity.OperationMode;
import com.jyqdesign.keysbankbackend.entity.OperationType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BackupPreferenceDTO {

    private String version;
    private LocalDateTime exportDate;

    private Account account;

    private List<OperationType> operationTypes;
    private List<OperationMode> operationModes;
    private List<Category> categories;
}