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
    private int currentSolde;
    private int initialBalance;
    private String lastUpdate;
    private int sinceYear;
    private String csvImportFolder;
    private int csvRowIgnored;
    private int csvRowDate;
    private int csvRowSolde;
    private int csvColumnNumber;
    private int csvColumnDate;
    private String csvDateFormat;
    private int csvColumnDescription;
    private int csvColumnCredit;
    private int csvColumnDebit;
    private int csvColumnValue;
}
