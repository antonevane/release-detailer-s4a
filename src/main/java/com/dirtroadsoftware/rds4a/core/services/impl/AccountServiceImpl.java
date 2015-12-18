package com.dirtroadsoftware.rds4a.core.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.google.common.collect.Lists;

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
    	Optional<Account> account =  accountRepository.findById(id);
    	return account.orElseThrow(() -> new AccountDoesNotExistException("Account doesn't exist! ID= " + id));
    }

    @Override
    public Account createAccount(Account account) {
        Account existingAccount = findByAccountName(account.getName());
        if (existingAccount != null) {
            throw new AccountExistsException("Account allready exist! [NAME] = " + account.getName());
        }
        return accountRepository.save(account);
    }

    @Override
    public ReleaseDashboard createReleaseDashboard(Long accountId, ReleaseDashboard dashboard) {
        ReleaseDashboard existingDashboard = dashboardRepository.findByTitle(dashboard.getTitle());
        if (existingDashboard != null) {
            throw new ReleaseDashboardExistsException();
        }
        
        Account owner = findAccount(accountId);
        
        ReleaseDashboard createdDashboard = dashboardRepository.save(dashboard);
        createdDashboard.setOwner(owner);
        return createdDashboard;
    }

    @Override
    public ReleaseDashboardList findReleaseDashboardsByAccount(Long accountId) {
    	// TODO wired way to test AccountDoesnt exist REMOVE/REFACTOR
        findAccount(accountId);
        return new ReleaseDashboardList(dashboardRepository.findByOwnerId((accountId)));
    }

    @Override
    public AccountList findAllAccounts() {
    	// TODO: Stream API
    	// https://github.com/spring-projects/spring-data-examples/tree/master/jpa/java8
        return new AccountList(Lists.newArrayList(accountRepository.findAll()));
    }

    @Override
    public Account findByAccountName(String name) {
    	Optional<Account> account =  accountRepository.findByName(name);
        return account.orElseThrow(() -> new AccountDoesNotExistException("Account doesn't exist"));
    }
}
