package com.jyqdesign.keysbankbackend.entity;
import lombok.Data;
@Data
public class CheckBook {
    private long idCheckBook;
    private long idProfile;
    private long firstCheckNumber;
    private long range;
    private boolean active;

    public CheckBook() {
    }
}
