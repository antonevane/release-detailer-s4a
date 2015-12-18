package com.dirtroadsoftware.rds4a.core.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;

/**
 *
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	Optional<Account> findById(Long id);
	Optional<Account> findByName(String name);
}
