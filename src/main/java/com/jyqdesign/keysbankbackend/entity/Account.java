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
    private Integer currentSolde;
    private Integer initialBalance;
    private String lastUpdate;
    private Integer sinceYear;
    private String csvImportFolder;
    private Integer csvRowIgnored;
    private Integer csvRowDate;
    private Integer csvRowSolde;
    private Integer csvColumnNumber;
    private Integer csvColumnDate;
    private String csvDateFormat;
    private Integer csvColumnDescription;
    private Integer csvColumnCredit;
    private Integer csvColumnDebit;
    private Integer csvColumnValue;
}
