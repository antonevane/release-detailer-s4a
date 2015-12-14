package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountRepositoryTest {
    /**
     * Repository auto-wired by Spring class runner. Bean definition is
     * in business-config.xml
     */
    @Autowired
    private AccountRepository repository;

    private Account account;

    @Before
    public void setup() {
        account = new Account();
        account.setName("Jeff");
        account.setPassword("abcdefg");

        repository.createAccount(account);
    }

    @Test
    @Transactional
    public void findAccount() {
        assertNotNull(repository.findOne(account.getId()));
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findAllAccounts() {
        Account account2 = new Account();
        account2.setName("Phil");
        account2.setPassword("abcdefg");
        repository.createAccount(account2);

        List<Account> accounts = Lists.newArrayList(repository.findAll());
        assertNotNull(accounts);
        assertEquals("Wrong number of accounts", 2, accounts.size());
    }

    @Test
    @Transactional
    public void findAccountByNameExisting() {
        assertNotNull(repository.findAccountByName(account.getName()));
    }
}
