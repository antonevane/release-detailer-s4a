package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.repositories.AccountRepository;
import com.dirtroadsoftware.rds4a.core.repositories.ReleaseDashboardRepository;
import com.dirtroadsoftware.rds4a.core.services.AccountService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountDoesNotExistException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountExistsException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardExistsException;
import com.dirtroadsoftware.rds4a.core.services.util.AccountList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;

/**
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ReleaseDashboardRepository dashboardRepository;

    @Override
    public Account findAccount(Long id) {
        return accountRepository.findAccount(id);
    }

    @Override
    public Account createAccount(Account account) {
        Account existingAccount = findByAccountName(account.getName());
        if (existingAccount != null) {
            throw new AccountExistsException();
        }
        return accountRepository.createAccount(account);
    }

    @Override
    public ReleaseDashboard createReleaseDashboard(Long accountId, ReleaseDashboard dashboard) {
        ReleaseDashboard existingDashboard = dashboardRepository.findReleaseDashboardByTitle(dashboard.getTitle());
        if (existingDashboard != null) {
            throw new ReleaseDashboardExistsException();
        }
        Account owner = findAccount(accountId);
        if (owner == null) {
            throw new AccountExistsException();
        }
        ReleaseDashboard createdDashboard = dashboardRepository.createReleaseDashboard(dashboard);
        createdDashboard.setOwner(owner);
        return createdDashboard;
    }

    @Override
    public ReleaseDashboardList findReleaseDashboardsByAccount(Long accountId) {
        Account account = findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        return new ReleaseDashboardList(dashboardRepository.findReleaseDashboardsByAccount(accountId));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepository.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepository.findAccountByName(name);
    }
}
