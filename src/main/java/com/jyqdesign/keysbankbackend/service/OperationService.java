package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Operation;

import java.sql.Timestamp;
import java.util.List;

public interface OperationService {
    List<Operation> readOperations(long idAccount, Timestamp start, Timestamp end);
    int createOperations(List<Operation> newOperations);
    Operation createOperation(Operation newOperation);
    int updateOperations(List<Operation> updatedOperations);
    Operation updateOperationById(long id, Operation updatedOperation);
    boolean deleteOperationById(long id);
}
