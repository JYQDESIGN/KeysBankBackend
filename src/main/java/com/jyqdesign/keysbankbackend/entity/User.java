package com.jyqdesign.keysbankbackend.entity;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
@Data
public class User {
    private long idUser;

    @Size(min = 3, max = 30, message = "Le pseudo doit contenir entre 3 et 30 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Le pseudo ne peut contenir que des chiffres et des lettres")
    private String pseudo;

    @Size(min = 2, max = 30, message = "Le prénom doit contenir entre 2 et 30 caractères")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ -]+$", message = "Le prénom ne peut contenir que des lettres, des espaces et des tirets")
    private String firstName;

    @Size(min = 2, max = 30, message = "Le nom doit contenir entre 2 et 30 caractères")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ -]+$", message = "Le nom ne peut contenir que des lettres, des espaces et des tirets")
    private String lastName;

    @Email(message = "L'''adresse email n'est pas valide")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{6,}$", message = "Le mot de passe doit contenir au moins 6 caractères, une majuscule, un chiffre et un caractère spécial")
    private String password;

    private LocalDateTime lastConnection;

    private List<Account> accounts;


}
