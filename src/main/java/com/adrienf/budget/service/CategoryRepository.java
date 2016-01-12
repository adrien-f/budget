package com.adrienf.budget.service;

import com.adrienf.budget.domain.Category;
import com.adrienf.budget.domain.Operation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource()
public interface CategoryRepository extends PagingAndSortingRepository<Category, UUID> {}
