package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Operation;

import java.sql.Timestamp;
import java.util.List;

public interface OperationService {
    List<Operation> readOperations(long idAccount, Timestamp start, Timestamp end);
    Operation createOperation(Operation newOperation);
    Operation updateOperationById(long id, Operation updatedOperation);
    boolean deleteOperationById(long id);

    int createOperations(List<Operation> newOperations);
    int updateOperations(List<Operation> updatedOperations);
    int deleteOperationsById(List<Integer> ids);
}
