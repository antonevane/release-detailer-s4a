package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
        accountRepository.createAccount(account);

        dashboard = new ReleaseDashboard();
        dashboard.setOwner(account);
        dashboard.setTitle("Jeff's Dashboard");
        dashboardRepository.createReleaseDashboard(dashboard);
    }

    @Test
    @Transactional
    public void findReleaseDashboard() {
        assertNotNull(dashboardRepository.findReleaseDashboard(dashboard.getId()));
    }

    @Test
    @Transactional
    public void findAllReleaseDashboards() {
        ReleaseDashboard dashboard2 = new ReleaseDashboard();
        dashboard2.setOwner(account);
        dashboard2.setTitle("Jeff's Second Dashboard");
        dashboardRepository.createReleaseDashboard(dashboard2);

        List<ReleaseDashboard> dashboards = dashboardRepository.findAllReleaseDashboards();

        assertNotNull(dashboards);
        assertEquals("Wrong number of dashboards", 2, dashboards.size());
    }

    @Test
    @Transactional
    public void findReleaseDashboardByTitle() {
        assertNotNull(dashboardRepository.findReleaseDashboardByTitle(dashboard.getTitle()));
    }

    @Test
    @Transactional
    public void findReleaseDashboardByAccount() {
        assertNotNull(dashboardRepository.findReleaseDashboardByAccount(dashboard.getOwner().getId()));
    }
}
