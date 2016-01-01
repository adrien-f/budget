package com.adrienf.budget.service;

import com.adrienf.budget.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Component("operationService")
@Transactional
public class OperationService {

    private final IOperationRepository operationRepository;

    @Autowired
    public OperationService(IOperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Iterable<Operation> findAll() {
        return this.operationRepository.findAll();
    }
}
