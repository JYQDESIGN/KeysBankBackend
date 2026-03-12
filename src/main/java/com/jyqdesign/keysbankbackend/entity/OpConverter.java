package com.jyqdesign.keysbankbackend.entity;

import com.jyqdesign.keysbankbackend.entity.enums.OpMode;
import com.jyqdesign.keysbankbackend.entity.enums.OpStatus;
import com.jyqdesign.keysbankbackend.entity.enums.OpType;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OpConverter {



    public static List<Operation> convert(long idAccount, String fileName) {
        System.out.println("TRY TO CONVERTED: " + fileName);

        List<OperationMode> modes = OperationMode.getDefaultOperationModes();
        List<OperationType> types = OperationType.getDefaultOperationTypes();
        OpStatus[] allStatus = OpStatus.values();

        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            List<Operation> operations = mapper.readValue(inputStream, new TypeReference<List<Operation>>() {});
            // set idAccount
            //System.out.println("RAW OPERATION: " + operations);
            operations.forEach(op -> {
                op.setIdAccount(idAccount);

                if (!OpConverter.isValidOpMode(modes, op.getMode())) {
                    op.setMode(OpMode.NONE);
                }

                if (!OpConverter.isValidOpType(types, op.getType())) {
                    op.setType(OpType.NONE);
                }

                if (!OpConverter.isValidOpStatus(allStatus, op.getStatus())) {
                    op.setStatus(OpStatus.NONE);
                }

                //date
                op.setDate(OpConverter.convertToDateTime(op.getDate()).toString());
            });
            //System.out.println("OP CONVERTED: " + operations);
            return operations;
        }catch (Exception e) {
            System.out.println("EXCEPTION: "+e);
            return null;
        }
    }

    public static boolean isValidOpMode(List<OperationMode> modes, OpMode mode) {
        for (OperationMode operationMode : modes) {
            if (operationMode.getValue() == mode) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidOpType(List<OperationType> types, OpType type) {
        for (OperationType operationType : types) {
            if (operationType.getValue() == type) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidOpStatus(OpStatus[] allStatus, OpStatus status) {
        for (OpStatus opStatus : allStatus) {
            if (status == opStatus) {
                return true;
            }
        }
        return false;
    }

    // 11/02/2026 to 2026-02-11 00:00:00.0000000
    public static LocalDateTime convertToDateTime(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        return localDate.atStartOfDay(); // 00:00:00
    }
}
