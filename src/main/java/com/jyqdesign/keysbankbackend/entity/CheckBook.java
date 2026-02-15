package com.jyqdesign.keysbankbackend.entity;
import lombok.Data;
@Data
public class CheckBook {
    private long idCheckBook;
    private long firstCheckNumber;
    private long range;
    private boolean active;
}
