package com.jyqdesign.keysbankbackend;

import com.jyqdesign.keysbankbackend.service.AccountPreferenceService;
import com.jyqdesign.keysbankbackend.service.AccountService;
import com.jyqdesign.keysbankbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeysBankBackendApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountPreferenceService accountPreferenceService;

    @Test
    void contextLoads() {
    }

    @Test
    void readAllUsers() {
        userService.readAll().forEach(System.out::println);
    }

    @Test
    void readUserById() {
        System.out.println("USER 1: "+userService.readById(1));
    }

    @Test
    void readAccountById() {
        System.out.println("ACCOUNT 1: "+accountService.readById(1));
    }

    @Test
    void createDefaultOperationType() {
        accountPreferenceService.createDefaultTypes(1);
    }

    @Test
    void createDefaultOperationMode() {
        accountPreferenceService.createDefaultModes(1);
    }

    @Test
    void readProfileTypes(){
        System.out.println("READ PROFILE TYPES: "+ accountPreferenceService.readPreferenceTypes(1));
    }

    @Test
    void readProfileModes(){
        System.out.println("READ PROFILE MODES: "+ accountPreferenceService.readPreferenceModes(1));
    }

    @Test
    void createDefaultCategories() {
        accountPreferenceService.createDefaultCategories(1);}

    @Test
    void readProfileCategories(){
        System.out.println("READ PROFILE CATEGORIES: "+ accountPreferenceService.readPreferenceCategories(1));
    }

}
