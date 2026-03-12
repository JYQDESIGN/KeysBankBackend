package com.jyqdesign.keysbankbackend.repository;

import com.jyqdesign.keysbankbackend.entity.Operation;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OperationRepository {

    List<Operation> readOperations(long idAccount, Timestamp start, Timestamp end);
    Operation createOperation(Operation operation);
    int createOperations(List<Operation> operations);
    Operation updateOperationById(long id, Operation updatedOperation);
    int updateOperations(List<Operation> operations);
    boolean deleteOperationById(long id);
}
