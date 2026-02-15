package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.*;
import com.jyqdesign.keysbankbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * GET /api/users
     * Récupère tous les utilisateurs
     */
    @GetMapping
    public List<User> readAll() {
        return userService.readAll();
    }

    // PROFILE

    @PutMapping("/profile/{id}")
    public UserAccountProfile updateProfile(
            @PathVariable long id,
            @RequestBody UserAccountProfile profile) {
        return userService.updateProfile(id, profile);
    }

    // CREDIT CARD

    @PostMapping("/card")
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard newCard) {
        CreditCard result = userService.createCard(newCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/card/{id}")
    public CreditCard updateCreditCard(
            @PathVariable long id,
            @RequestBody boolean active) {
        return userService.updateCard(id, active);
    }

    @DeleteMapping("/card/{id}")
    public boolean deleteCreditCard(@PathVariable long id) {
        return userService.deleteCard(id);
    }

    // CHECK BOOK

    @PostMapping("/check")
    public ResponseEntity<CheckBook> createCheckBook(@RequestBody CheckBook newCheck) {
        CheckBook result = userService.createCheckBook(newCheck);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/check/{id}")
    public CheckBook updateCheckBook(
            @PathVariable long id,
            @RequestBody boolean active) {
        return userService.updateCheckBook(id, active);
    }

    @DeleteMapping("/check/{id}")
    public boolean deleteCheckBook(@PathVariable long id) {
        return userService.deleteCheckBook(id);
    }
}

