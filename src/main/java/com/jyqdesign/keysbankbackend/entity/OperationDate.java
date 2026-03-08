package com.jyqdesign.keysbankbackend.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class OperationDate {
    private String start;
    private String end;

    // Formatter pour convertir "18/02/2026"
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private Timestamp convertToTimestamp(String date) {
        LocalDate localDate = LocalDate.parse(date, FORMATTER);
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    public Timestamp getStartTimeStamp() {
        return convertToTimestamp(start);
    }

    public Timestamp getEndTimeStamp() {
        return convertToTimestamp(end);
    }
}
