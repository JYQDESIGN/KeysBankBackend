package com.jyqdesign.keysbankbackend.entity;

import com.jyqdesign.keysbankbackend.entity.enums.OpMode;
import com.jyqdesign.keysbankbackend.entity.enums.OpStatus;
import com.jyqdesign.keysbankbackend.entity.enums.OpType;
import lombok.Data;

@Data
public class Operation {
    private long idOp;
    private long idAccount;
    private String category;
    private String subCategory;
    private OpMode mode;
    private OpType type;
    private OpStatus status;
    private String description;
    private String comment;
    private String date;
    private long value;
}

