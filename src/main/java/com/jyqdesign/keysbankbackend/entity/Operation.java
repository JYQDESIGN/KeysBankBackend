package com.jyqdesign.keysbankbackend.entity;

import lombok.Data;

@Data
public class Operation {
    private long idOp;
    private long idAccount;
    private String category;
    private String subCategory;
    private String mode;
    private String type;
    private String status;
    private String description;
    private String comment;
    private String date;
    private long value;
}

