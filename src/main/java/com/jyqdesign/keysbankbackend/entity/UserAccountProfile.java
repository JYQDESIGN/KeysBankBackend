package com.jyqdesign.keysbankbackend.entity;
import lombok.Data;

import java.util.List;

@Data
public class UserAccountProfile {
    private long idProfile;
    private long idAccount;
    private String role;
    private User user;
    private List<CreditCard> creditCards;
    private List<CheckBook> checkBooks;
}
