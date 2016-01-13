package com.adrienf.budget.service;

import com.adrienf.budget.domain.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;


public interface OperationRepository extends PagingAndSortingRepository<Operation, UUID> {}
