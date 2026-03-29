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
        Balance readBalance = balanceRepository.readBalance(idAccount, year);
        if (readBalance != null) {
            //balance exists
            return readBalance;
        } else {
            //create new balance
            return balanceRepository.createBalance(idAccount, year);
        }
    }

    @Override
    public Balance updateBalanceById(long idBalanceSheet, Balance updatedBalance) {
        // update balance y
        Balance currentBalanceUpdated =  balanceRepository.updateBalanceById(idBalanceSheet, updatedBalance);
        if (currentBalanceUpdated != null) {
            // update balance y+1 : y+1 startingBalance = y endingSolde
            // get y+1 balance
            Balance nextBalance = this.readBalance(currentBalanceUpdated.getIdAccount(), currentBalanceUpdated.getYear() + 1);
            nextBalance.setStartingBalance(currentBalanceUpdated.getEndingSolde());
            // update balance y+1
            Balance nextBalanceUpdated = balanceRepository.updateBalanceById(nextBalance.getIdBalanceSheet(), nextBalance);
            return nextBalanceUpdated != null ? currentBalanceUpdated : null;
        }
        return null;
    }

    @Override
    public Balance readFullBalance(long idAccount) {
        return balanceRepository.readFullBalance(idAccount);
    }
}
