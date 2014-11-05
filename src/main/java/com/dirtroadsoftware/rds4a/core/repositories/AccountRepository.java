package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;

import java.util.List;

/**
 *
 */
public interface AccountRepository {
    public Account findAccount(Long id);
    public Account createAccount(Account account);
    public List<Account> findAllAccounts();
    public Account findAccountByName(String name);
}
