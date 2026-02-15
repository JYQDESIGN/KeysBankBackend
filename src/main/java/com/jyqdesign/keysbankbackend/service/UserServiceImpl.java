package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.User;
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
}
