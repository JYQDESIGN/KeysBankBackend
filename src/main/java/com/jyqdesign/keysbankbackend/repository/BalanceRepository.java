package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Balance;

import java.util.List;

public interface BalanceRepository {
    Balance readBalance(long idAccount, long year);

    Balance updateBalanceById(long idBalanceSheet, Balance updatedBalance);

    Balance readFullBalance(long idAccount);
}
