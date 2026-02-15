package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthentController {

    private final UserService userService;

    /**
     * GET /api/login/{login, password}
     * Récupère un compte par son id
     */
    @PostMapping
    public User login(@RequestBody LoginRequest request) {
        System.out.println("login: "+request);
        return userService.readByPseudoAndPassword(
                request.getLogin(),
                request.getPassword()
        );
    }
}
