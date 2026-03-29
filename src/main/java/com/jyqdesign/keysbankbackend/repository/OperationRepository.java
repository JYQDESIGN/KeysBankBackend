package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Operation;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OperationRepository {

    List<Operation> readOperations(long idAccount, Timestamp start, Timestamp end);
    Operation createOperation(Operation operation);
    Operation updateOperationById(long id, Operation updatedOperation);
    boolean deleteOperationById(long id);

    int createOperations(List<Operation> operations);
    int updateOperations(List<Operation> operations);
    int deleteOperationsById(List<Long> ids);
}
