package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.User;

import java.util.List;

public interface UserRepository {

    void createUser(User user);

    List<User> readAll();

    User readById(long id);

    User readByPseudoAndPassword(String pseudo, String password);

    void updateUser(User user);

    void deleteUser(long id);

}
