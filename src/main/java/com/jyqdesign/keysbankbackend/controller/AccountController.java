package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.Account;
import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.repository.dto.AccountPreferencesDTO;
import com.jyqdesign.keysbankbackend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * GET /api/account/{id}
     * Récupère un compte par son id
     */
    @GetMapping("/{id}")
    public Account readById(@PathVariable long id) {
        System.out.println("get account by id: "+id);
        return accountService.readById(id);
    }

    @PostMapping("")
    public Account createAccount(@RequestBody Account newAccount) {
        System.out.println("create account: "+newAccount);
        //return null;
        return accountService.createAccount(newAccount);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAccountById(
            @PathVariable long id) {
        return accountService.deleteAccount(id);
    }
}

