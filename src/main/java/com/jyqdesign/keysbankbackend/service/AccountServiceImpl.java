package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.repository.AccountPreferenceRepository;
import com.jyqdesign.keysbankbackend.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository;
    AccountPreferenceRepository accountPreferenceRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AccountPreferenceRepository accountPreferenceRepository) {
        this.accountRepository = accountRepository;
        this.accountPreferenceRepository = accountPreferenceRepository;
    }

    @Override
    public Account createAccount(Account newAccount) {
        Account account = this.accountRepository.createAccount(newAccount);
        //create defaults preferences
        this.accountPreferenceRepository.createDefaultModes(account.getIdAccount());
        this.accountPreferenceRepository.createDefaulTypes(account.getIdAccount());
        this.accountPreferenceRepository.createDefaultCategories(account.getIdAccount());
        return account;
    }

    @Override
    public List<Account> readAll() {
        return List.of();
    }

    @Override
    public Account readById(long id) {
        return this.accountRepository.readById(id);
    }

    @Override
    public boolean deleteAccount(long id) {
        return this.accountRepository.deleteAccount(id);
    }

    @Override
    public Account updateAccountById(long id, Account updatedAccount) {
        return this.accountRepository.updateAccountById(id, updatedAccount);
    }
}
