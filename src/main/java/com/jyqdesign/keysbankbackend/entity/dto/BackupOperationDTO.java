package com.jyqdesign.keysbankbackend.entity.dto;

import com.jyqdesign.keysbankbackend.entity.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BackupOperationDTO {

    private String version;
    private LocalDateTime exportDate;

    private int accountId;
    private int year;

    private List<Operation> operations;
    private Balance balance;
}
