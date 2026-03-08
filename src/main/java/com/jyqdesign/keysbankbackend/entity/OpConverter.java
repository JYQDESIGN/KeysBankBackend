package com.jyqdesign.keysbankbackend.entity;

import com.jyqdesign.keysbankbackend.entity.enums.OpMode;
import com.jyqdesign.keysbankbackend.entity.enums.OpType;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class OpConverter {



    public static List<Operation> convert(long idAccount, String fileName) {
        List<OperationMode> modes = OperationMode.getDefaultOperationModes();
        List<OperationType> types = OperationType.getDefaultOperationTypes();
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            List<Operation> operations = mapper.readValue(inputStream, new TypeReference<List<Operation>>() {});
            // set idAccount
            operations.forEach(op -> {
                op.setIdAccount(idAccount);

                if (!OpConverter.isValidOpMode(modes, op.getMode())) {
                    op.setMode(OpMode.NONE.name());
                }

                if (!OpConverter.isValidOpType(types, op.getType())) {
                    op.setType(OpType.NONE.name());
                }
            });
            //System.out.println("OP CONVERTED: " + operations);
            return operations;
        }catch (Exception e) {
            return null;
        }
    }

    public static boolean isValidOpMode(List<OperationMode> modes, String mode) {
        for (OperationMode operationMode : modes) {
            if (operationMode.getValue().name().equalsIgnoreCase(mode)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidOpType(List<OperationType> types, String type) {
        for (OperationType operationType : types) {
            if (operationType.getValue().name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
