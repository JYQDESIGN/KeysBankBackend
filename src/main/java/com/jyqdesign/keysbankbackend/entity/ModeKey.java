package com.jyqdesign.keysbankbackend.entity;

import lombok.Data;
@Data
public class ModeKey {
    private long idMode;
    private long idKey;
    private String key;

    public ModeKey() {
    }

    public ModeKey(String key) {
        this.key = key;
    }
}
