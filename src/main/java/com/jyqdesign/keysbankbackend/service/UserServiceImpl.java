package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.CheckBook;
import com.jyqdesign.keysbankbackend.entity.CreditCard;
import com.jyqdesign.keysbankbackend.entity.User;
import com.jyqdesign.keysbankbackend.entity.UserAccountProfile;
import com.jyqdesign.keysbankbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public List<User> readAll() {
        return this.userRepository.readAll();
    }

    @Override
    public User readById(long id) {
        return this.userRepository.readById(id);
    }

    @Override
    public User readByPseudoAndPassword(String pseudo, String password) {
        return this.userRepository.readByPseudoAndPassword(pseudo, password);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(long id) {

    }

    // CREDIT CARD CRUD

    @Override
    public CreditCard createCard(CreditCard newCard) {
        return this.userRepository.createCard(newCard);
    }

    @Override
    public CreditCard updateCard(long id, boolean active) {
        return this.userRepository.updateCard(id, active);
    }

    @Override
    public boolean deleteCard(long id) {
        return this.userRepository.deleteCard(id);
    }

    // CHECK BOOK CRUD

    @Override
    public CheckBook createCheckBook(CheckBook newCheck) {
        return this.userRepository.createCheckBook(newCheck);
    }

    @Override
    public CheckBook updateCheckBook(long id, boolean active) {
        return this.userRepository.updateCheckBox(id, active);
    }

    @Override
    public boolean deleteCheckBook(long id) {
        return this.userRepository.deleteCheckBook(id);
    }

    // PROFILE

    @Override
    public UserAccountProfile updateProfile(long id, UserAccountProfile profile) {
        return this.userRepository.updateProfile(id, profile);
    }
}
