package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.CheckBook;
import com.jyqdesign.keysbankbackend.entity.CreditCard;
import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.entity.UserAccountProfile;

import java.util.List;

public interface UserRepository {

    void createUser(User user);

    List<User> readAll();

    User readById(long id);

    User readByPseudoAndPassword(String pseudo, String password);

    void updateUser(User user);

    void deleteUser(long id);

    // CREDIT CARD CRUD

    CreditCard createCard(CreditCard newCard);
    CreditCard updateCard(long id, boolean active);
    boolean deleteCard(long id);

    // CHECK BOOK CRUD

    CheckBook createCheckBook(CheckBook newCheck);
    CheckBook updateCheckBox(long id, boolean active);
    boolean deleteCheckBook(long id);

    // PROFILE

    UserAccountProfile updateProfile(long id, UserAccountProfile profile);
}
