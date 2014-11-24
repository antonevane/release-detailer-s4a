package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseService;
import com.dirtroadsoftware.rds4a.core.services.ReleaseSiteService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseSiteNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test the MA Release web controller. Uses Mockito and Spring MockMVC to test controller behavior
 */
public class MaReleaseControllerTest {

    /** Mock service primed with responses */
    @Mock
    private MaReleaseService service;

    /** Controller where mock service is injected */
    @InjectMocks
    private MaReleaseController controller;

    // Spring MockMVC
    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Mockito initialization
        MockitoAnnotations.initMocks(this);

        // Spring MockMVC initialization
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getExistingReleaseSite() throws Exception {
        MaRelease release = new MaRelease();
        release.setId(1L);
        release.setRegion(1);
        release.setSiteName("Tram Breakwater");
        release.setAddress("123 Palm Drive");
        release.setSite(12345);

        // Tell Mockito to return the releaseSite when 1L is searched
        when(service.findMaRelease(1L)).thenReturn(release);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/releases/1"))
                .andExpect(jsonPath("$.region", is(release.getRegion())))
                .andExpect(jsonPath("$.site", is(release.getSite())))
                .andExpect(jsonPath("$.siteName", is(release.getSiteName())))
                .andExpect(jsonPath("$.address", is(release.getAddress())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/releases/1"))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_SELF))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getNonExistingReleaseSite() throws Exception {
        // Tell Mockito to return null when 1L is searched
        when(service.findMaRelease(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/releases/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getExistingMaReleaseByRtn() throws Exception {
        MaRelease release = new MaRelease();
        release.setId(1L);
        release.setRegion(1);
        release.setSite(12345);
        release.setSiteName("Tram Breakwater");
        release.setAddress("123 Palm Drive");


        // Tell Mockito to return the releaseSite when 1L is searched
        when(service.findMaReleaseByRtn("01-012345")).thenReturn(release);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/releases/ma/01-012345"))
                .andExpect(jsonPath("$.region", is(release.getRegion())))
                .andExpect(jsonPath("$.site", is(release.getSite())))
                .andExpect(jsonPath("$.siteName", is(release.getSiteName())))
                .andExpect(jsonPath("$.address", is(release.getAddress())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/releases/1"))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_SELF))))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
