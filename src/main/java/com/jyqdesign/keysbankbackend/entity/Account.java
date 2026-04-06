package com.jyqdesign.keysbankbackend.entity;
import lombok.Data;

import java.util.List;

@Data
public class Account {
    private long idAccount;
    private List<UserAccountProfile> profiles;
    private String name;
    private String bank;
    private String reference;
    private Long currentSolde;
    private Long initialBalance;
    private String lastUpdate;
    private Long sinceYear;
    private String csvImportFolder;
    private Long csvRowIgnored;
    private Long csvRowDate;
    private Long csvRowSolde;
    private Long csvColumnNumber;
    private Long csvColumnDate;
    private String csvDateFormat;
    private Long csvColumnDescription;
    private Long csvColumnCredit;
    private Long csvColumnDebit;
    private Long csvColumnValue;
}
