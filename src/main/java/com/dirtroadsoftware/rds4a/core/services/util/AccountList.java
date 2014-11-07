package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;

import java.util.List;

/**
 *
 */
public class AccountList {
    private List<Account> accounts;

    public AccountList(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
