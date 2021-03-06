package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.services.AccountService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountDoesNotExistException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountExistsException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardExistsException;
import com.dirtroadsoftware.rds4a.core.services.util.AccountList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
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

    private ArgumentCaptor<Account> accountCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        accountCaptor = ArgumentCaptor.forClass(Account.class);
    }

    @Test
    public void findExistingAccount() throws Exception {
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");
        owner.setPassword("abcdefg");

        when(service.findAccount(eq(3L))).thenReturn(owner);

        mockMvc.perform(get("/rest/accounts/3"))
                .andDo(print())
                .andExpect(jsonPath("$.password", is(nullValue())))
                .andExpect(jsonPath("$.name", is(owner.getName())))
                .andExpect(jsonPath("$.links[0].href", endsWith("accounts/3")))
                .andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)))
                .andExpect(status().isOk());
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
                .andExpect(jsonPath("$.links[0].href", endsWith("/accounts/3")))
                .andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)))
                .andExpect(header().string("Location", endsWith("/accounts/3")))
                .andExpect(status().isCreated())
                .andDo(print());

         verify(service).createAccount(accountCaptor.capture());

        String password = accountCaptor.getValue().getPassword();
        assertEquals("abcdefg", password);
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
        owner.setName("Test Name");
        owner.setId(3L);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard.setId(2L);

        when(service.createReleaseDashboard(eq(3L), any(ReleaseDashboard.class))).thenReturn(dashboard);
        when(service.findByAccountName(anyString())).thenReturn(owner);


        // Setup the SecurityContextHolder with a mocked context
        UserDetails principal = Mockito.mock(UserDetails.class);
        when(principal.getUsername()).thenReturn(owner.getName());
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(principal);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

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
        owner.setName("Test Name");
        owner.setId(3L);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard.setId(2L);

        when(service.createReleaseDashboard(eq(3L), any(ReleaseDashboard.class))).thenThrow(new AccountDoesNotExistException());
        when(service.findByAccountName(anyString())).thenReturn(owner);

        // Setup the SecurityContextHolder with a mocked context
        UserDetails principal = Mockito.mock(UserDetails.class);
        when(principal.getUsername()).thenReturn(owner.getName());
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(principal);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(post(URI.create("/rest/accounts/3/dashboards"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Hello board\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createReleaseDashboardExistingDashboard() throws Exception {
        Account owner = new Account();
        owner.setName("Test Name");
        owner.setId(3L);

        when(service.createReleaseDashboard(eq(3L), any(ReleaseDashboard.class))).thenThrow(new ReleaseDashboardExistsException());
        when(service.findByAccountName(anyString())).thenReturn(owner);

        // Setup the SecurityContextHolder with a mocked context
        UserDetails principal = Mockito.mock(UserDetails.class);
        when(principal.getUsername()).thenReturn(owner.getName());
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(principal);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(post(URI.create("/rest/accounts/3/dashboards"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Hello board\"}"))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void findAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<Account>();
        Account owner1 = new Account();
        owner1.setName("Jeff");
        owner1.setPassword("abcdefg");
        owner1.setId(1l);
        Account owner2 = new Account();
        owner2.setName("Phil");
        owner2.setPassword("gfedcba");
        owner2.setId(2L);
        accounts.add(owner1);
        accounts.add(owner2);

        AccountList accountList = new AccountList(accounts);

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/rest/accounts"))
                .andDo(print())
                .andExpect(jsonPath("$.accounts[0].name", is(owner1.getName())))
                .andExpect(jsonPath("$.accounts[0].password", nullValue()))
                .andExpect(jsonPath("$.accounts[1].name", is(owner2.getName())))
                .andExpect(jsonPath("$.accounts[1].password", nullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllAccountsNoAccounts() throws Exception {
        AccountList accountList = new AccountList(new ArrayList<Account>());

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/rest/accounts"))
                .andDo(print())
                .andExpect(jsonPath("$.accounts", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void findByAccountNameExistingAccount() throws Exception {
        Account owner = new Account();
        owner.setId(3L);
        owner.setName("Jeff");
        owner.setPassword("abcdefg");

        when(service.findByAccountName(any(String.class))).thenReturn(owner);

        mockMvc.perform(get("/rest/accounts").param("name","Jeff"))
                .andDo(print())
                .andExpect(jsonPath("$.accounts[0].name", is(owner.getName())))
                .andExpect(status().isOk());
    }

    @Test
    public void findByAccountNameNonExistingAccount() throws Exception {
        when(service.findByAccountName(any(String.class))).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(get("/rest/accounts").param("name", "Jeff"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllDashboardsExistingAccount() throws Exception {
        Account owner = new Account();
        owner.setId(3L);

        ReleaseDashboard dashboard = new ReleaseDashboard();
        dashboard.setOwner(owner);
        dashboard.setTitle("Hello board");
        dashboard.setId(2L);

        ReleaseDashboard dashboard2 = new ReleaseDashboard();
        dashboard2.setOwner(owner);
        dashboard2.setTitle("Hello Again board");
        dashboard2.setId(3L);

        List<ReleaseDashboard> dashboards = new ArrayList<ReleaseDashboard>();
        dashboards.add(dashboard);
        dashboards.add(dashboard2);

        ReleaseDashboardList dashboardsList = new ReleaseDashboardList(dashboards);
        when(service.findReleaseDashboardsByAccount(eq(3L))).thenReturn(dashboardsList);

        mockMvc.perform(get("/rest/accounts/3/dashboards"))
                .andDo(print())
                .andExpect(jsonPath("$.dashboards[0].title", is("Hello board")))
                .andExpect(jsonPath("$.dashboards[1].title", is("Hello Again board")))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllDashboardsNonExistingAccount() throws Exception {
        when(service.findReleaseDashboardsByAccount(any(Long.class))).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(get("/rest/accounts/3/dashboards"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
