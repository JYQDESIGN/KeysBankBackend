package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.service.UserService;
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

}

