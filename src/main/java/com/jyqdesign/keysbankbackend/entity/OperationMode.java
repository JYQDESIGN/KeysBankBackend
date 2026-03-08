package com.jyqdesign.keysbankbackend.entity;

import com.jyqdesign.keysbankbackend.entity.enums.OpMode;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OperationMode {
    private long idOperationMode;
    private long idAccount;
    private String label;
    private OpMode value;
    private String color;
    private String icon;

    private List<ModeKey> keys;

    public OperationMode() {
    }

    public OperationMode(String label, OpMode value, String color, String icon, List<ModeKey> keys) {
        this.label = label;
        this.value = value;
        this.color = color;
        this.icon = icon;
        this.keys = keys;
    }

    public static ArrayList<OperationMode> getDefaultOperationModes() {
        ArrayList<OperationMode> list = new ArrayList<>();

        ArrayList<ModeKey> keys = new ArrayList<>();
        list.add(new OperationMode("Non définie", OpMode.NONE, "#BBBBBB", "bi-question-circle", keys));

        keys = new ArrayList<>();
        keys.add(new ModeKey("ACHAT CB"));
        list.add(new OperationMode("Carte bleue", OpMode.CREDIT_CARD, "#2060DD", "bi-credit-card", keys));

        keys = new ArrayList<>();
        keys.add(new ModeKey("PRELEVEMENT DE"));
        keys.add(new ModeKey("COTISATION"));
        list.add(new OperationMode("Prélèvement", OpMode.DIRECT_DEBIT, "#FF8000", "bi-upload", keys));

        keys = new ArrayList<>();
        keys.add(new ModeKey("REMISE DE CHEQUES"));
        keys.add(new ModeKey("REMISE DE CHEQUE"));
        list.add(new OperationMode("Chèque reçu", OpMode.BANK_CHECK_IN, "#10CC10", "bi-credit-card-2-front-fill", keys));

        keys = new ArrayList<>();
        keys.add(new ModeKey("CHEQUE"));
        list.add(new OperationMode("Chèque émis", OpMode.BANK_CHECK_OUT, "#CC1111", "bi-credit-card-2-front", keys));

        keys = new ArrayList<>();
        keys.add(new ModeKey("VIREMENT DE"));
        keys.add(new ModeKey("VERSEMENT"));
        list.add(new OperationMode("Virement reçu", OpMode.BANK_TRANSFER_IN, "#40AA40", "bi-box-arrow-in-up-right", keys));

        keys = new ArrayList<>();
        keys.add(new ModeKey("VIREMENT POUR"));
        keys.add(new ModeKey("VIREMENT PERMANENT POUR"));
        list.add(new OperationMode("Virement émis", OpMode.BANK_TRANSFER_OUT, "#EE4040", "bi-box-arrow-up-right", keys));

        keys = new ArrayList<>();
        keys.add(new ModeKey("DIFFERE"));
        list.add(new OperationMode("Débit différé", OpMode.DEFERRED_OUT, "#00AAAA", "bi-clock-history", keys));
        return list;
    }
}
