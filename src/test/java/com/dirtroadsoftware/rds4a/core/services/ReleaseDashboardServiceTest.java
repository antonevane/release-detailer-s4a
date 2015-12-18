package com.dirtroadsoftware.rds4a.core.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseSiteList;


/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReleaseDashboardServiceTest {
    @Autowired AccountService accountService;
    @Autowired ReleaseDashboardService dashboardService;

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findAllReleaseDashboards() {
        Account owner = new Account();
        owner.setName("Jeff");
        owner.setPassword("abcdefg");
        owner = accountService.createAccount(owner);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard = accountService.createReleaseDashboard(owner.getId(), dashboard);

        ReleaseDashboardList dashboards = dashboardService.findAllReleaseDashboards();

        assertNotNull(dashboards);
        assertEquals(1, dashboards.getDashboards().size());
    }


    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findReleaseDashboard() {
        Account owner = new Account();
        owner.setName("Jeff");
        owner.setPassword("abcdefg");
        owner = accountService.createAccount(owner);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard = accountService.createReleaseDashboard(owner.getId(), dashboard);

        ReleaseDashboard foundDashboard = dashboardService.findReleaseDashboard(dashboard.getId());

        assertNotNull(foundDashboard);
        assertEquals(dashboard.getTitle(), foundDashboard.getTitle());
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void createReleaseSite() {
        Account owner = new Account();
        owner.setName("Jeff");
        owner.setPassword("abcdefg");
        owner = accountService.createAccount(owner);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard = accountService.createReleaseDashboard(owner.getId(), dashboard);

        ReleaseSite site = new ReleaseSite();
        site.setRegion(3);
        site.setSite(12345);
        site.setDashboard(dashboard);

        ReleaseSite createdSite = dashboardService.createReleaseSite(dashboard.getId(), site);
        assertNotNull(createdSite);
        assertEquals(site.getSite(), createdSite.getSite());
    }

    @Test
    @Transactional
    @Ignore("Uses actual database")
    public void findAllReleaseSites() {
        Account owner = new Account();
        owner.setName("Jeff");
        owner.setPassword("abcdefg");
        owner = accountService.createAccount(owner);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard = accountService.createReleaseDashboard(owner.getId(), dashboard);

        ReleaseSite site = new ReleaseSite();
        site.setRegion(3);
        site.setSite(12345);
        site.setDashboard(dashboard);

        dashboardService.createReleaseSite(dashboard.getId(), site);

        ReleaseSiteList releaseSites = dashboardService.findAllReleaseSites(dashboard.getId());
        assertNotNull(releaseSites);
        assertEquals(1, releaseSites.getSites().size());
        assertEquals(12345, releaseSites.getSites().get(0).getSite());

    }
}
