package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.services.AccountService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountDoesNotExistException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountExistsException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardExistsException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class AccountControllerTest {
    @Mock
    private AccountService service;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void findExistingAccount() throws Exception {
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");

        when(service.findAccount(eq(3L))).thenReturn(owner);

        mockMvc.perform(get("/rest/accounts/3"))
                .andExpect(jsonPath("$.name", is(owner.getName())))
                .andExpect(jsonPath("$.links[0].href", endsWith("accounts/3")))
                .andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findNonExistingAccount() throws Exception {
        when(service.findAccount(eq(3L))).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(get("/rest/accounts/3"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void createAccountNonExistingName() throws Exception {
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");
        owner.setPassword("abcdefg");

        when(service.createAccount(any(Account.class))).thenReturn(owner);

        mockMvc.perform(post(URI.create("/rest/accounts"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jeff\",\"password\":\"abcdefg\"}"))
                .andExpect(jsonPath("$.name", is(owner.getName())))
                .andExpect(jsonPath("$.links[0].href", endsWith("accounts/3")))
                .andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void createAccountExistingName() throws Exception {
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");
        owner.setPassword("abcdefg");

        when(service.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

        mockMvc.perform(post(URI.create("/rest/accounts"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jeff\",\"password\":\"abcdefg\"}"))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    public void createReleaseDashboardExistingAccount() throws Exception {
        Account owner = new Account();
        owner.setId(3L);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard.setId(2L);

        when(service.createReleaseDashboard(eq(3L), any(ReleaseDashboard.class))).thenReturn(dashboard);

        mockMvc.perform(post(URI.create("/rest/accounts/3/dashboards"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Hello board\"}"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(dashboard.getTitle())))
                .andExpect(jsonPath("$.links[0].href", endsWith("/release-dashboards/2")))
                .andExpect(header().string("Location", endsWith("/release-dashboards/2")))
                .andExpect(status().isCreated());
    }


    @Test
    public void createReleaseDashboardNonExistingAccount() throws Exception {
        Account owner = new Account();
        owner.setId(3L);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard.setId(2L);

        when(service.createReleaseDashboard(eq(3L), any(ReleaseDashboard.class))).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(post(URI.create("/rest/accounts/3/dashboards"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Hello board\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createReleaseDashboardExistingDashboard() throws Exception {
        Account owner = new Account();
        owner.setId(3L);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard.setId(2L);

        when(service.createReleaseDashboard(eq(3L), any(ReleaseDashboard.class))).thenThrow(new ReleaseDashboardExistsException());

        mockMvc.perform(post(URI.create("/rest/accounts/3/dashboards"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Hello board\"}"))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
