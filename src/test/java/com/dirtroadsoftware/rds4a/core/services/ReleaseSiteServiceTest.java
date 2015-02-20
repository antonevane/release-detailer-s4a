package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
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
@ContextConfiguration("classpath:/spring/business-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReleaseSiteServiceTest {
    @Autowired
    ReleaseSiteService siteService;

    @Autowired
    ReleaseDashboardService dashboardService;

    @Autowired
    AccountService accountService;
    private Account owner;
    private ReleaseDashboard dashboard;
    private ReleaseSite site;
    private ReleaseSite updatedSite;

    @Before
    public void setup() {
        owner = new Account();
        owner.setName("Jeff");
        owner.setPassword("abcdefg");
        owner = accountService.createAccount(owner);

        dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard = accountService.createReleaseDashboard(owner.getId(), dashboard);

        site = new ReleaseSite();
        site.setRegion(3);
        site.setSite(12345);
        site.setDashboard(dashboard);

        site = dashboardService.createReleaseSite(dashboard.getId(), site);
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findReleaseSite() {
        ReleaseSite foundSite = siteService.findReleaseSite(site.getId());
        assertNotNull(foundSite);
        assertEquals(site.getSite(), foundSite.getSite());
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void updateReleaseSite() {
        site.setSite(11111);
        updatedSite = siteService.updateReleaseSite(site.getId(), site);
        assertNotNull(updatedSite);
        assertEquals(11111, updatedSite.getSite());

        ReleaseSite foundSite = siteService.findReleaseSite(site.getId());
        assertNotNull(foundSite);
        assertEquals(11111, foundSite.getSite());
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void deleteReleaseSite() {
        ReleaseSite deletedSite = siteService.deleteReleaseSite(site.getId());
        assertNotNull(deletedSite);

        ReleaseSite foundSite = siteService.findReleaseSite(site.getId());
        assertNull(foundSite);
    }
}
