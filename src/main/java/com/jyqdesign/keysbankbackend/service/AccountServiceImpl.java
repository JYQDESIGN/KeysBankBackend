package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.repository.AccountPreferenceRepository;
import com.jyqdesign.keysbankbackend.repository.AccountRepository;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(Account account) {

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
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(long id) {

    }
}
