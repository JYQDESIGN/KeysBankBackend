package com.jyqdesign.keysbankbackend.entity;
import lombok.Data;
@Data
public class CreditCard {
    private long idCreditCard;
    private long idProfile;
    private long reference;
    private boolean active;

    public CreditCard() {
    }
}
