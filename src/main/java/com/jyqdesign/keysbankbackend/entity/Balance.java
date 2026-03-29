package com.jyqdesign.keysbankbackend.entity;

import lombok.Data;

@Data
public class Balance {
    private long idBalanceSheet;
    private long idAccount;
    private long year;
    private long startingBalance;
    private long endingSolde;
    private long operationCount;

    private long cumulCredit;
    private long cumulDebit;
    private long cumulNone;
    private long cumulSaving;
    private long cumulSurvival;
    private long cumulCultural;
    private long cumulOptional;
    private long cumulExtra;
    private long cumulIncome;
}
