package com.jyqdesign.keysbankbackend;

import com.jyqdesign.keysbankbackend.entity.OpConverter;
import com.jyqdesign.keysbankbackend.entity.Operation;
import com.jyqdesign.keysbankbackend.service.AccountPreferenceService;
import com.jyqdesign.keysbankbackend.service.AccountService;
import com.jyqdesign.keysbankbackend.service.OperationService;
import com.jyqdesign.keysbankbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class KeysBankBackendApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountPreferenceService accountPreferenceService;

    @Autowired
    OperationService operationService;

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

    @Test
    void opConvertion(String fileName) {
        List<Operation> ops = OpConverter.convert(1, fileName);
        operationService.createOperations(ops);

    }

    @Test
    void initDb() {
        this.createDefaultOperationType();
        this.createDefaultOperationMode();
        this.createDefaultCategories();
        //opérations
        for(int y=2007 ; y<2027 ; y++) {
            if (y!=2020 && y!=2021 && y!=2023) {
                String fileLabel = y+".json";
                this.opConvertion(fileLabel);
            }
        }
    }

}
