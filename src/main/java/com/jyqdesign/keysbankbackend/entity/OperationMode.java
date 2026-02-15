package com.jyqdesign.keysbankbackend.entity;

import com.jyqdesign.keysbankbackend.entity.enums.OpMode;

import java.util.ArrayList;
import lombok.Data;
@Data
public class OperationMode {
    private long idOperationMode;
    private long idAccount;
    private String label;
    private OpMode value;
    private String color;
    private String icon;

    public OperationMode() {
    }

    public OperationMode(String label, OpMode value, String color, String icon) {
        this.label = label;
        this.value = value;
        this.color = color;
        this.icon = icon;
    }

    public static ArrayList<OperationMode> getDefaultOperationModes() {
        ArrayList<OperationMode> list = new ArrayList<>();
        list.add(new OperationMode("Non définie", OpMode.NONE, "#808080", "bi-question-circle"));
        list.add(new OperationMode("Carte", OpMode.CREDIT_CARD,"#0000FF", "bi-credit-card"));
        list.add(new OperationMode("Prélèvement", OpMode.DIRECT_DEBIT,"#FFFF00", "bi-upload"));
        list.add(new OperationMode("Chèque reçu", OpMode.BANK_CHECK_IN,"#00FF00", "bi-credit-card-2-front-fill"));
        list.add(new OperationMode("Chèque émis", OpMode.BANK_CHECK_OUT,"#008000", "bi-credit-card-2-front"));
        list.add(new OperationMode("Virement reçu", OpMode.BANK_TRANSFER_IN,"#FF0000", "bi-box-arrow-in-up-right"));
        list.add(new OperationMode("Virement émis", OpMode.BANK_TRANSFER_OUT,"#800000", "bi-box-arrow-up-right"));
        list.add(new OperationMode("Débit différé", OpMode.DEFERRED_OUT,"#004040", "bi-clock-history"));
        return list;
    }
}
