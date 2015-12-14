package com.dirtroadsoftware.rds4a.core.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;

/**
 *
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{
    public Account createAccount(Account account);
    
    public Account findAccountByName(String name);
}
