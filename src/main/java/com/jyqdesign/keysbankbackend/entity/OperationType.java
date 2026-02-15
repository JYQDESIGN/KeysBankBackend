package com.jyqdesign.keysbankbackend.entity;

import com.jyqdesign.keysbankbackend.entity.enums.OpType;
import lombok.Data;

import java.util.ArrayList;

@Data
public class OperationType {
    private long idOperationType;
    private long idAccount;
    private String label;
    private OpType value;
    private String color;
    private String icon;

    public OperationType() {
    }

    public OperationType(String label, OpType value, String color, String icon) {
        this.label = label;
        this.value = value;
        this.color = color;
        this.icon = icon;
    }

    public static ArrayList<OperationType> getDefaultOperationTypes() {
        ArrayList<OperationType> list = new ArrayList<>();
        list.add(new OperationType("Non définie", OpType.NONE, "#808080","bi-question-circle"));
        list.add(new OperationType("Epargne", OpType.SAVING, "#00DD00", "bi-piggy-bank"));
        list.add(new OperationType("Survie", OpType.SURVIVAL, "#FF0000", "bi-heart-pulse"));
        list.add(new OperationType("Culture", OpType.CULTURAL, "#00DDDD", "bi-journal-text"));
        list.add(new OperationType("Facultatif", OpType.OPTIONAL, "#DDDD00", "bi-cone-striped"));
        list.add(new OperationType("Extra", OpType.EXTRA, "#FF00FF", "bi-gift"));
        list.add(new OperationType("Entrée", OpType.INCOME, "#80DD80", "bi-box-arrow-in-down"));
        return list;
    }
}
