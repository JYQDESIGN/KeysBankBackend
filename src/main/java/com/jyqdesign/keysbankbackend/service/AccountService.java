package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.entity.User;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> readAll();

    Account readById(long id);

    Account updateAccount(Account account);

    boolean deleteAccount(long id);

}
