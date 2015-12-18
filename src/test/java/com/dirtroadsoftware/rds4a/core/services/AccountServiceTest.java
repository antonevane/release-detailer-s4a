package com.dirtroadsoftware.rds4a.core.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.repositories.AccountRepository;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findAccountExistingOwner() throws Exception {
        Account owner = new Account();
        owner.setName("Foo");
        owner.setPassword("abcdefg");
        Account ownerCreated = accountRepository.save(owner);
        assertNotNull(ownerCreated);

        Account ownerFound = accountService.findAccount(ownerCreated.getId());
        assertNotNull(ownerFound);
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findAccountNonExistingOwner() throws Exception {
        Account ownerFound = accountService.findAccount(3L);
        assertNull(ownerFound);
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void createAccount() throws Exception {
        Account owner = new Account();
        owner.setName("Foo");
        owner.setPassword("abcdefg");
        Account ownerCreated = accountService.createAccount(owner);
        assertNotNull(ownerCreated);
    }

    @Test
    @Transactional
    public void createReleaseDashboardExistingOwner() throws Exception {
        Account owner = new Account();
        owner.setName("Foo");
        owner.setPassword("abcdefg");
        owner = accountService.createAccount(owner);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");

        ReleaseDashboard createdDashboard = accountService.createReleaseDashboard(owner.getId(), dashboard);
        assertNotNull(createdDashboard);
        assertEquals("Wrong board title", dashboard.getTitle(), createdDashboard.getTitle());

    }
}
