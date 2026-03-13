package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Balance;
import com.jyqdesign.keysbankbackend.repository.BalanceRepository;
import com.jyqdesign.keysbankbackend.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService{

    BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance readBalance(long idAccount, long year) {
        return balanceRepository.readBalance(idAccount, year);
    }

    @Override
    public Balance updateBalanceById(long idBalanceSheet, Balance updatedBalance) {
        return balanceRepository.updateBalanceById(idBalanceSheet, updatedBalance);
    }

    @Override
    public Balance readFullBalance(long idAccount) {
        return balanceRepository.readFullBalance(idAccount);
    }
}
