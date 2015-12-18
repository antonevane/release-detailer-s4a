package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
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
@ContextConfiguration("/spring/business-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReleaseDashboardRepositoryTest {
    @Autowired
    private ReleaseDashboardRepository dashboardRepository;
    @Autowired
    private AccountRepository accountRepository;
    private ReleaseDashboard dashboard;
    private Account account;

    @Before
    public void setup() {
        account = new Account();
        account.setName("Jeff");
        account.setPassword("abcdefg");
        accountRepository.save(account);

        dashboard = new ReleaseDashboard();
        dashboard.setOwner(account);
        dashboard.setTitle("Jeff's Dashboard");
        dashboardRepository.save(dashboard);
    }

    @Test
    @Transactional
    public void findReleaseDashboard() {
        assertNotNull(dashboardRepository.findOne(dashboard.getId()));
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findAllReleaseDashboards() {
        ReleaseDashboard dashboard2 = new ReleaseDashboard();
        dashboard2.setOwner(account);
        dashboard2.setTitle("Jeff's Second Dashboard");
        dashboardRepository.save(dashboard2);

        List<ReleaseDashboard> dashboards = Lists.newArrayList(dashboardRepository.findAll());

        assertNotNull(dashboards);
        assertEquals("Wrong number of dashboards", 2, dashboards.size());
    }

    @Test
    @Transactional
    public void findReleaseDashboardByTitle() {
        assertNotNull(dashboardRepository.findByTitle(dashboard.getTitle()));
    }

    @Test
    @Transactional
    public void findReleaseDashboardByAccount() {
        assertNotNull(dashboardRepository.findByOwnerId(dashboard.getOwner().getId()));
    }
}
