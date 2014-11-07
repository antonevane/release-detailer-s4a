package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.services.ReleaseDashboardService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardNotFoundException;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseSiteList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class ReleaseDashboardControllerTest {
    @Mock
    private ReleaseDashboardService service;

    @InjectMocks
    private ReleaseDashboardController releaseDashboardController;

    // Spring MockMVC
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(releaseDashboardController).build();
    }

    @Test
    public void getReleaseDashboard() throws Exception {
        // Create a ReleaseDashboard to use in testing
        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setTitle("Jeff's Dashboard");
        dashboard.setId(2L);
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");
        owner.setPassword("abcdefg");
        dashboard.setOwner(owner);

        // Prime the mock object. Tell Mockito to return the dashboard when
        when(service.findReleaseDashboard(2L)).thenReturn(dashboard);

        // Invoke the controller and verify behavior
        mockMvc.perform(get("/rest/release-dashboards/2"))
                .andExpect(jsonPath("$.title", is(dashboard.getTitle())))
                .andExpect(jsonPath("$.links[0].href", endsWith("/release-dashboards/2")))
                .andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)))
                .andExpect(jsonPath("$.links[1].href", endsWith("/release-dashboards/2/release-sites")))
                .andExpect(jsonPath("$.links[1].rel", is("release-sites")))
                .andExpect(jsonPath("$.links[2].rel", is("owner")))
                .andExpect(jsonPath("$.links[2].href", endsWith("/accounts/3")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findAllReleaseSites() throws Exception {
        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setTitle("Jeff's Dashboard");
        dashboard.setId(2L);
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");
        owner.setPassword("abcdefg");
        dashboard.setOwner(owner);

        List<ReleaseSite> sites = new ArrayList<ReleaseSite>();
        ReleaseSite site = new ReleaseSite();
        site.setId(5L);
        site.setRegion(3);
        site.setSite(12345);
        site.setDashboard(dashboard);
        sites.add(site);

        ReleaseSite site2 = new ReleaseSite();
        site2.setId(6L);
        site2.setRegion(4);
        site2.setSite(67890);
        site2.setDashboard(dashboard);
        sites.add(site2);

        ReleaseSiteList siteList = new ReleaseSiteList(dashboard.getId(), sites);
        siteList.setReleaseSiteId(4L);

        when(service.findAllReleaseSites(2L)).thenReturn(siteList);

        mockMvc.perform(get("/rest/release-dashboards/2/release-sites"))
                .andExpect(jsonPath("$.sites[0].region", is(site.getRegion())))
                .andExpect(jsonPath("$.sites[0].site", is(site.getSite())))
                .andExpect(jsonPath("$.sites[0].links[0].href", endsWith("/release-sites/5")))
                .andExpect(jsonPath("$.sites[0].links[0].rel", is(Link.REL_SELF)))
                .andExpect(jsonPath("$.sites[1].region", is(site2.getRegion())))
                .andExpect(jsonPath("$.sites[1].site", is(site2.getSite())))
                .andExpect(jsonPath("$.sites[1].links[0].href", endsWith("/release-sites/6")))
                .andExpect(jsonPath("$.sites[1].links[0].rel", is(Link.REL_SELF)))
                .andExpect(status().isOk());

    }

    @Test
    public void findAllReleaseSitesNonExistingDashboard() throws Exception {
        when(service.findAllReleaseSites(2L)).thenThrow(new ReleaseDashboardNotFoundException());

        mockMvc.perform(get("/rest/release-dashboards/2/release-sites"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void findAllReleaseDashboards() throws Exception {
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");
        owner.setPassword("abcdefg");

        ReleaseDashboard dashboard1 = new ReleaseDashboard();
        dashboard1.setTitle("Jeff's Dashboard");
        dashboard1.setId(2L);
        dashboard1.setOwner(owner);

        ReleaseDashboard dashboard2 = new ReleaseDashboard();
        dashboard2.setTitle("Jeff's Other Dashboard");
        dashboard2.setId(3L);
        dashboard2.setOwner(owner);

        List<ReleaseDashboard> dashboards = new ArrayList<ReleaseDashboard>();
        dashboards.add(dashboard1);
        dashboards.add(dashboard2);

        ReleaseDashboardList dashboardList = new ReleaseDashboardList(dashboards);

        when(service.findAllReleaseDashboards()).thenReturn(dashboardList);

        mockMvc.perform(get("/rest/release-dashboards"))
                .andExpect(jsonPath("$.dashboards[*].title", hasItems(endsWith(dashboard1.getTitle()), endsWith(dashboard2.getTitle()))))
                .andDo(print());
    }

    @Test
    public void createReleaseSiteExistingReleaseDashboard() throws Exception {
        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setId(2L);

        ReleaseSite site = new ReleaseSite();
        site.setId(5L);
        site.setRegion(3);
        site.setSite(12345);
        site.setDashboard(dashboard);

        when(service.createReleaseSite(eq(2L), any(ReleaseSite.class))).thenReturn(site);

        mockMvc.perform(post("/rest/release-dashboards/2/release-sites")
                .content("{\"site\":12345,\"region\":3}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.site", is(12345)))
                .andExpect(jsonPath("$.region", is(3)))
                .andExpect(jsonPath("$.links[0].href", endsWith("/release-sites/5")))
                .andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createReleaseSiteNonExistingReleaseDashboard() throws Exception {
        when(service.createReleaseSite(eq(2L), any(ReleaseSite.class))).thenThrow(new ReleaseDashboardNotFoundException());

        mockMvc.perform(post("/rest/release-dashboards/2/release-sites")
                .content("{\"site\":12345,\"region\":3}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
