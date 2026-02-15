package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.*;

import java.util.List;

public interface UserService {
    void createUser(User user);

    List<User> readAll();

    //USER CRUD
    User readById(long id);
    User readByPseudoAndPassword(String pseudo, String password);
    void updateUser(User user);
    void deleteUser(long id);

    //CREDIT CARD CRUD
    CreditCard createCard(CreditCard newCard);
    CreditCard updateCard(long id, boolean active);
    boolean deleteCard(long id);

    //CHECK BOOK CRUD
    CheckBook createCheckBook(CheckBook newCheck);
    CheckBook updateCheckBook(long id, boolean active);
    boolean deleteCheckBook(long id);

    //PROFILE
    UserAccountProfile updateProfile(long id, UserAccountProfile profile);
}
