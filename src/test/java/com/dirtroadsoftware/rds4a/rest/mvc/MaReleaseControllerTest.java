package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseService;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseList;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test the MA Release web controller. Uses Mockito and Spring MockMVC to test controller behavior
 */
public class MaReleaseControllerTest {

    /** Mock service primed with responses */
    @Mock
    private MaReleaseService releaseService;

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
        release.setRtn("1-0012345");
        release.setNotification(Calendar.getInstance().getTime());
        release.setStatusDate(Calendar.getInstance().getTime());

        // Tell Mockito to return the releaseSite when 1L is searched
        when(releaseService.findMaRelease(1L)).thenReturn(release);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/releases/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.region", is(release.getRegion())))
                .andExpect(jsonPath("$.site", is(release.getSite())))
                .andExpect(jsonPath("$.siteName", is(release.getSiteName())))
                .andExpect(jsonPath("$.address", is(release.getAddress())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/releases/1"))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_SELF))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/1-0012345/actions"))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is("actions"))))
                ;
    }

    @Test
    public void getNonExistingReleaseSite() throws Exception {
        // Tell Mockito to return null when 1L is searched
        when(releaseService.findMaRelease(1L)).thenReturn(null);

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
        release.setRtn("1-0012345");
        release.setNotification(Calendar.getInstance().getTime());
        release.setStatusDate(Calendar.getInstance().getTime());

        // Tell Mockito to return the releaseSite when 1L is searched
        when(releaseService.findMaReleaseByRtn("01-012345")).thenReturn(release);

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

    @Test
    public void findAllActions() throws Exception {
        MaRelease release = new MaRelease();
        release.setId(1L);
        release.setRegion(1);
        release.setSite(12345);
        release.setSiteName("Tram Breakwater");
        release.setAddress("123 Palm Drive");
        release.setRtn("1-0012345");

        MaAction action = new MaAction();
        action.setId(123L);
        action.setRelease(release);
        action.setDate(new LocalDate("2008-12-05").toDate());
        action.setStatus("ISSUED");
        action.setAction("NOR");
        action.setRtn("1-0012345");

        release.setActions(Arrays.asList(action));

        when(releaseService.findMaReleaseWithActionsByRtn("1-0012345")).thenReturn(release);

        mockMvc.perform(get("/rest/releases/ma/1-0012345/actions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actions[0].rtn", is("1-0012345")))
                .andExpect(jsonPath("$.actions[0].date", is("2008-12-05")))
                .andExpect(jsonPath("$.actions[0].status", is("ISSUED")))
                .andExpect(jsonPath("$.actions[0].action", is("NOR")))
                .andExpect(jsonPath("$.actions[0].links[*].href", hasItem(endsWith("/actions/123"))))
                .andExpect(jsonPath("$.actions[0].links[*].href", hasItem(endsWith("/releases/ma/1-0012345"))))
                .andExpect(jsonPath("$.actions[0].links[*].rel", hasItem(is("release"))))
                ;

    }

    @Test
    public void getExistingMaReleasesByTown() throws Exception {
        MaRelease release1 = createRelease(1L, 1, 54321, "This site", "314 Meadow Dr", "1-0054321", "MY TOWN");
        release1.setNotification(Calendar.getInstance().getTime());
        release1.setStatusDate(Calendar.getInstance().getTime());

        MaRelease release2 = createRelease(2L, 2, 12345, "Tram Breakwater", "123 Palm Drive", "1-0012345", "MY TOWN");
        release2.setNotification(Calendar.getInstance().getTime());
        release2.setStatusDate(Calendar.getInstance().getTime());

        // Tell Mockito to return the releaseSite when 1L is searched
        MaReleaseList releases = new MaReleaseList(Arrays.asList(new MaRelease[]{release1, release2}));

        when(releaseService.findMaReleasesByTown("MY TOWN")).thenReturn(releases);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/releases/ma/town/MY TOWN"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.releases[1].region", is(release2.getRegion())))
                .andExpect(jsonPath("$.releases[1].site", is(release2.getSite())))
                .andExpect(jsonPath("$.releases[1].siteName", is(release2.getSiteName())))
                .andExpect(jsonPath("$.releases[1].address", is(release2.getAddress())))
                .andExpect(jsonPath("$.releases[1].links[*].href", hasItem(endsWith("/releases/2"))))
                .andExpect(jsonPath("$.releases[1].links[*].rel", hasItem(is(Link.REL_SELF))));
    }

    @Test
    public void getExistingMaReleasesByTownPagination() throws Exception {
        MaRelease release1 = createRelease(1L, 1, 54321, "This site", "314 Meadow Dr", "1-0054321", "MY TOWN");
        MaAction action1 = new MaAction();
        action1.setAction("ACTION_ONE");
        action1.setStatus("STATUS_ONE");
        List<MaAction> actions1  = new ArrayList<MaAction>();
        actions1.add(action1);
        release1.setActions(actions1);
        release1.setNotification(Calendar.getInstance().getTime());
        release1.setStatusDate(Calendar.getInstance().getTime());

        MaRelease release2 = createRelease(2L, 2, 12345, "Tram Breakwater", "123 Palm Drive", "1-0012345", "MY TOWN");
        List<MaAction> actions2  = new ArrayList<MaAction>();
        MaAction action2 = new MaAction();
        action2.setAction("ACTION_TWO");
        action2.setStatus("STATUS_TWO");
        actions2.add(action2);
        release2.setActions(actions2);
        release2.setNotification(Calendar.getInstance().getTime());
        release2.setStatusDate(Calendar.getInstance().getTime());

        // Tell Mockito to return the releaseSite when 1L is searched
        MaReleaseList releases = new MaReleaseList(Arrays.asList(new MaRelease[]{release1, release2}));

        when(releaseService.findMaReleasesByTown("MY TOWN", "notification", "DESC", 3, 2)).thenReturn(releases);
        when(releaseService.countMaReleasesByTown("MY TOWN")).thenReturn(10l);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/releases/ma?town=MY%20TOWN&offset=3&limit=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.releases[1].region", is(release2.getRegion())))
                .andExpect(jsonPath("$.releases[1].site", is(release2.getSite())))
                .andExpect(jsonPath("$.releases[1].siteName", is(release2.getSiteName())))
                .andExpect(jsonPath("$.releases[1].address", is(release2.getAddress())))
                .andExpect(jsonPath("$.releases[1].links[*].href", hasItem(endsWith("/releases/2"))))
                .andExpect(jsonPath("$.releases[1].links[*].rel", hasItem(is(Link.REL_SELF))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_PREVIOUS))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/releases/ma?town=MY%20TOWN&offset=2&limit=2"))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_NEXT))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/releases/ma?town=MY%20TOWN&offset=4&limit=2"))))
        ;
    }

    @Test
    public void getExistingMaReleasesPageOne() throws Exception {
        MaRelease release1 = createRelease(1L, 1, 54321, "This site", "314 Meadow Dr", "1-0054321", "MY TOWN");
        MaAction action1 = new MaAction();
        action1.setAction("ACTION_ONE");
        action1.setStatus("STATUS_ONE");
        List<MaAction> actions1  = new ArrayList<MaAction>();
        actions1.add(action1);
        release1.setActions(actions1);
        release1.setNotification(Calendar.getInstance().getTime());
        release1.setStatusDate(Calendar.getInstance().getTime());

        MaRelease release2 = createRelease(2L, 2, 12345, "Tram Breakwater", "123 Palm Drive", "1-0012345", "MY TOWN");
        List<MaAction> actions2  = new ArrayList<MaAction>();
        MaAction action2 = new MaAction();
        action2.setAction("ACTION_TWO");
        action2.setStatus("STATUS_TWO");
        actions2.add(action2);
        release2.setActions(actions2);
        release2.setNotification(Calendar.getInstance().getTime());
        release2.setStatusDate(Calendar.getInstance().getTime());

        // Tell Mockito to return the releaseSite when 1L is searched
        MaReleaseList releases = new MaReleaseList(Arrays.asList(new MaRelease[]{release1, release2}));

        when(releaseService.findMaReleasesByTown("MY TOWN", "notification", "DESC", 0, 2)).thenReturn(releases);
        when(releaseService.countMaReleasesByTown("MY TOWN")).thenReturn(10l);

        // Ask Spring MockMVC to issue a GET request against our controller
        mockMvc.perform(get("/rest/releases/ma?town=MY%20TOWN&offset=0&limit=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.links[*].rel", not(hasItem(is(Link.REL_PREVIOUS)))))
                .andExpect(jsonPath("$.links[*].rel", hasItem(is(Link.REL_NEXT))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/releases/ma?town=MY%20TOWN&offset=1&limit=2"))))
        ;
    }

    private MaRelease createRelease(long id, int region, int site, String siteName, String address, String rtn, String town) {
        MaRelease release1 = new MaRelease();
        release1.setId(id);
        release1.setRegion(region);
        release1.setSite(site);
        release1.setSiteName(siteName);
        release1.setAddress(address);
        release1.setRtn(rtn);
        release1.setTown(town);
        return release1;
    }

}
