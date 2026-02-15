package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Account;

import java.util.List;

public interface AccountRepository {
    void createAccount(Account account);

    List<Account> readAll();

    Account readById(long id);

    void updateAccount(Account account);

    void deleteAccount(long id);
}
