package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;

import java.util.List;

public interface AccountService {
    void createAccount(Account account);

    List<Account> readAll();

    Account readById(long id);

    void updateAccount(Account account);

    void deleteAccount(long id);
}
