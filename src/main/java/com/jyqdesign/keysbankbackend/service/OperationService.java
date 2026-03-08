package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Operation;
import com.jyqdesign.keysbankbackend.entity.SubCategory;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OperationService {
    void createOperations(List<Operation> operations);
    List<Operation> readOperations(long idAccount, Timestamp start, Timestamp end);

    Operation createOperation(Operation newOperation);
    Operation updateOperationById(long id, Operation updatedOperation);
    boolean deleteOperationById(long id);
}
