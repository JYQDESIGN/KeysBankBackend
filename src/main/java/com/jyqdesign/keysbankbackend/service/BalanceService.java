package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Balance;

public interface BalanceService {
    Balance readBalance(long idAccount, long year);

    Balance updateBalanceById(long idBalanceSheet, Balance updatedBalance);

    Balance readFullBalance(long idAccount);
}
