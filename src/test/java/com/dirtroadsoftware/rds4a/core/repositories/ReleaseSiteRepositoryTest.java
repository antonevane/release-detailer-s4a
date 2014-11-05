package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/business-config.xml")
public class ReleaseSiteRepositoryTest {
    @Autowired
    ReleaseSiteRepository siteRepository;

    @Autowired
    ReleaseDashboardRepository dashboardRepository;

    @Autowired
    AccountRepository accountRepository;

    ReleaseSite site;
    ReleaseDashboard dashboard;
    Account account;

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

        site = new ReleaseSite();
        site.setRegion(1);
        site.setSite(12345);
        site.setDashboard(dashboard);
        siteRepository.createReleaseSite(site);
    }

    @Test
    @Transactional
    public void findReleaseSite() {
        assertNotNull(siteRepository.findReleaseSite(site.getId()));
    }

    @Test
    @Transactional
    public void findByReleaseDashboard() {
        ReleaseSite site2 = new ReleaseSite();
        site2.setRegion(1);
        site2.setSite(12345);
        site2.setDashboard(dashboard);
        siteRepository.createReleaseSite(site2);

    }

    @Test
    @Transactional
    public void updateReleaseSite() {
        ReleaseSite updatedSite = new ReleaseSite();
        updatedSite.setRegion(2);
        updatedSite.setSite(23456);
        updatedSite.setDashboard(dashboard);
        siteRepository.updateReleaseSite(site.getId(), updatedSite);
        ReleaseSite actualReleaseSite = siteRepository.findReleaseSite(site.getId());
        assertEquals("Wrong region", 2, actualReleaseSite.getRegion());
        assertEquals("Wrong site", 23456, actualReleaseSite.getSite());
    }

    @Test
    @Transactional
    public void deleteReleaseSite() {
        siteRepository.deleteReleaseSite(site.getId());
        assertNull(siteRepository.findReleaseSite(site.getId()));
    }
}
