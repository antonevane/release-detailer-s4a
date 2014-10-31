package com.dirtroadsoftware.rds4a.mvc;

import com.dirtroadsoftware.rds4a.core.services.ReleaseSiteService;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseSiteController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 */
public class ReleaseSiteControllerTest {

    @Mock
    private ReleaseSiteService service;

    @InjectMocks
    private ReleaseSiteController releaseSiteController;

    // Spring MockMVC
    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Mockito initialization
        MockitoAnnotations.initMocks(this);

        // Spring MockMVC initialization
        mockMvc = MockMvcBuilders.standaloneSetup(releaseSiteController).build();
    }

    @Test
    public void getExistingReleaseSite() throws Exception {
        ReleaseSite site = new ReleaseSite();
        site.setId(1L);
        site.setRegion(1);
        site.setSite(12345);

        // Tell Mockito to return the releaseSite when 1L is searched
        when(service.find(1L)).thenReturn(site);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/release-sites/1"))
                .andExpect(jsonPath("$.region", is(site.getRegion())))
                .andExpect(jsonPath("$.site", is(site.getSite())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/release-sites/1"))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_SELF))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getNonExistingReleaseSite() throws Exception {
        // Tell Mockito to return null when 1L is searched
        when(service.find(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/release-sites/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deleteExistingReleaseSite() throws Exception {
        ReleaseSite site = new ReleaseSite();
        site.setId(1L);
        site.setRegion(1);
        site.setSite(12345);

        when(service.delete(site.getId())).thenReturn(site);

        mockMvc.perform(delete("/rest/release-sites/1"))
                .andExpect(jsonPath("$.region", is(site.getRegion())))
                .andExpect(jsonPath("$.site", is(site.getSite())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/release-sites/1"))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_SELF))))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingReleaseSite() throws Exception {
        when(service.delete(1L)).thenReturn(null);

        mockMvc.perform(delete("/rest/release-sites/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingReleaseSite() throws Exception {
        ReleaseSite updatedSite = new ReleaseSite();
        updatedSite.setId(1L);
        updatedSite.setRegion(2);
        updatedSite.setSite(12345);

        when(service.update(eq(1L), any(ReleaseSite.class))).thenReturn(updatedSite);

        mockMvc.perform(put("/rest/release-sites/1")

                .content("{\"region\":2,\"site\":12345}")
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.region", is(updatedSite.getRegion())))
                    .andExpect(jsonPath("$.site", is(updatedSite.getSite())))
                    .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/release-sites/1"))))
                    .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_SELF))))
                    .andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingReleaseSite() throws Exception {
        when(service.update(eq(1L), any(ReleaseSite.class))).thenReturn(null);

        mockMvc.perform(put("/rest/release-sites/1")
                .content("{\"region\":2,\"site\":12345}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
