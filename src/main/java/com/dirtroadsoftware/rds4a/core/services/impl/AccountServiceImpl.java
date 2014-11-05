package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.repositories.AccountRepository;
import com.dirtroadsoftware.rds4a.core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account findAccount(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Account createAccount(Account account) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ReleaseDashboard createReleaseDashboard(Long accountId, ReleaseDashboard dashboard) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
