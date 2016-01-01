package com.adrienf.budget.web;

import com.adrienf.budget.domain.Operation;
import com.adrienf.budget.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController()
public class OperationsController {

    @Autowired
    private OperationService operationService;

    @RequestMapping(value = "/operations")
    @Transactional(readOnly = true)
    public Iterable<Operation> index() {
        return operationService.findAll();
    }
}
