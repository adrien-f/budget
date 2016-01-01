package com.adrienf.budget.service;

import com.adrienf.budget.domain.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface IOperationRepository extends PagingAndSortingRepository<Operation, UUID> {

}
