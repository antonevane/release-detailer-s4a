package com.dirtroadsoftware.rds4a.rest.mvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseTownService;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseTownList;

/**
 * Test the MA Release Town web controller.
 */
public class MaReleaseTownControllerTest {

    @Mock
    private MaReleaseTownService townService;

    /** Controller where mock service is injected */
    @InjectMocks
    private MaReleaseTownController controller;

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
    public void getTownForZipcode() throws Exception {
        MaReleaseTown town1 = new MaReleaseTown();
        town1.setName("TOWN ONE");
        town1.setRegion(1);
        town1.setReleases(111);
        town1.setZipCode("12345");

        // Tell Mockito to return the releaseSite when 1L is searched
        when(townService.findReleaseTownByZipCode("12345")).thenReturn(town1);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/towns/ma/12345"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("Town One")))
                .andExpect(jsonPath("$.region", is(1)))
                .andExpect(jsonPath("$.numReleases", is(111)))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_SELF))))
        ;
    }

    @Test
    public void getAllTowns() throws Exception {
        MaReleaseTown town1 = new MaReleaseTown();
        town1.setName("TOWN ONE");
        town1.setRegion(1);
        town1.setReleases(111);
        town1.setZipCode("12345");

        MaReleaseTown town2 = new MaReleaseTown();
        town2.setName("TOWN TWO");
        town2.setRegion(4);
        town2.setReleases(543);
        town2.setZipCode("54321");

        MaReleaseTownList towns = new MaReleaseTownList(Arrays.asList(new MaReleaseTown[]{town1, town2}));

        // Tell Mockito to return the releaseSite when 1L is searched
        when(townService.findAllReleaseTowns()).thenReturn(towns);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/towns/ma"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.towns[0].name", is("Town One")))
                .andExpect(jsonPath("$.towns[0].region", is(1)))
                .andExpect(jsonPath("$.towns[0].numReleases", is(111)))
                .andExpect(jsonPath("$.towns[0].links[*].rel", hasItem(is(Link.REL_SELF))))
                .andExpect(jsonPath("$.towns[1].name", is("Town Two")))
                .andExpect(jsonPath("$.towns[1].region", is(4)))
                .andExpect(jsonPath("$.towns[1].numReleases", is(543)))
                .andExpect(jsonPath("$.towns[1].links[*].rel", hasItem(is(Link.REL_SELF))))
                ;
    }

}
