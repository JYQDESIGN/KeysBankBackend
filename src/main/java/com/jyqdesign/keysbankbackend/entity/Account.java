package com.jyqdesign.keysbankbackend.entity;
import lombok.Data;

import java.util.List;

@Data
public class Account {
    private long idAccount;
    private List<UserAccountProfile> profiles;
    private String name;
    private String bank;
    private String reference;
}
