package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Account;

import java.util.List;

public interface AccountRepository {
    Account createAccount(Account account);

    List<Account> readAll();

    Account readById(long id);

    //Account updateAccount(Account account);

    boolean deleteAccount(long id);

    Account updateAccountById(long id, Account updatedAccount);
}
