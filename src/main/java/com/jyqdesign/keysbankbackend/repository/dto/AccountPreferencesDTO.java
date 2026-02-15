package com.jyqdesign.keysbankbackend.repository.dto;

import com.jyqdesign.keysbankbackend.entity.Category;
import com.jyqdesign.keysbankbackend.entity.OperationMode;
import com.jyqdesign.keysbankbackend.entity.OperationType;
import lombok.Data;

import java.util.List;

@Data
public class AccountPreferencesDTO {

    private List<OperationType> types;
    private List<OperationMode> modes;
    private List<Category> categories;

    public AccountPreferencesDTO(List<OperationType> types, List<OperationMode> modes, List<Category> categories) {
        this.types = types;
        this.modes = modes;
        this.categories = categories;
    }
}
