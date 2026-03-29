package com.jyqdesign.keysbankbackend.service;

import com.jyqdesign.keysbankbackend.entity.Operation;
import com.jyqdesign.keysbankbackend.repository.OperationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService{

    OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Operation> readOperations(long idAccount, Timestamp start, Timestamp end) {
        return operationRepository.readOperations(idAccount, start, end);
    }

    @Override
    public Operation createOperation(Operation newOperation) {
        return operationRepository.createOperation(newOperation);
    }

    @Override
    public int createOperations(List<Operation> newOperations) {
        return operationRepository.createOperations(newOperations);
    }

    @Override
    public Operation updateOperationById(long id, Operation updatedOperation) {
        return operationRepository.updateOperationById(id, updatedOperation);
    }

    @Override
    public int updateOperations(List<Operation> updatedOperations) {
        return operationRepository.updateOperations(updatedOperations);
    }

    @Override
    public boolean deleteOperationById(long id) {
        return operationRepository.deleteOperationById(id);
    }

    @Override
    public int deleteOperationsById(List<Long> ids) {
        return operationRepository.deleteOperationsById(ids);
    }
}
