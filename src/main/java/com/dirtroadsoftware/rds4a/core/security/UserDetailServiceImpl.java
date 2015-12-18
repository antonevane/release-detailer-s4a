package com.dirtroadsoftware.rds4a.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.services.AccountService;

/**
 *
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = accountService.findByAccountName(name);
        if (account == null) {
            throw new UsernameNotFoundException("No user found with name " + name);
        }
        return new AccountUserDetails(account);
    }
}
