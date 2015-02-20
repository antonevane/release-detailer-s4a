package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.services.AccountService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountDoesNotExistException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountExistsException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardExistsException;
import com.dirtroadsoftware.rds4a.core.services.util.AccountList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.rest.exceptions.BadRequestException;
import com.dirtroadsoftware.rds4a.rest.exceptions.ConflictException;
import com.dirtroadsoftware.rds4a.rest.exceptions.ForbiddenException;
import com.dirtroadsoftware.rds4a.rest.exceptions.NotFoundException;
import com.dirtroadsoftware.rds4a.rest.resources.AccountListResource;
import com.dirtroadsoftware.rds4a.rest.resources.AccountResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardListResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.AccountListResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.AccountResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseDashboardListResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseDashboardResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 */
@Controller
@RequestMapping(value = "/rest/accounts")
public class AccountController {
    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @RequestMapping(value="/{accountId}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> findAccount(@PathVariable Long accountId) {
        try {
            Account account = service.findAccount(accountId);
            AccountResourceAsm asm = new AccountResourceAsm();
            AccountResource res = asm.toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } catch (AccountDoesNotExistException ex) {
            throw new NotFoundException(ex);
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        try {
            Account account = service.createAccount(sentAccount.toAccount());
            AccountResource newResource = new AccountResourceAsm().toResource(account);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(newResource.getLink(Link.REL_SELF).getHref()));
            return new ResponseEntity<AccountResource>(newResource, headers, HttpStatus.CREATED);
        } catch (AccountExistsException ex) {
            throw new ConflictException(ex);
        }
    }

    @RequestMapping(value="/{accountId}/dashboards", method=RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<ReleaseDashboardResource> createReleaseDashboard(@PathVariable Long accountId,
                                                                           @RequestBody ReleaseDashboardResource sentDashboard) {
        // Get the authenticated principal
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            // See if the logged in user has the same ID as is in the requested resource URL
            Account loggedIn = service.findByAccountName(userDetails.getUsername());
            if (loggedIn.getId() == accountId) {
                try {
                    ReleaseDashboard dashboard = service.createReleaseDashboard(accountId, sentDashboard.toReleaseDashboard());
                    ReleaseDashboardResource createdDashboard = new ReleaseDashboardResourceAsm().toResource(dashboard);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(createdDashboard.getLink(Link.REL_SELF).getHref()));
                    return new ResponseEntity<ReleaseDashboardResource>(createdDashboard, headers, HttpStatus.CREATED);
                } catch (AccountDoesNotExistException ex) {
                    throw new BadRequestException(ex);
                } catch (ReleaseDashboardExistsException ex) {
                    throw new ConflictException(ex);
                }
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(required = false) String name) {
        AccountList accounts = null;
        if (name == null) {
            accounts = service.findAllAccounts();
        } else {
            try {
                Account account = service.findByAccountName(name);
                if (account == null) {
                    accounts = new AccountList(new ArrayList<Account>());
                } else {
                    accounts = new AccountList(Arrays.asList(account));
                }
            } catch (AccountDoesNotExistException ex) {
                throw new NotFoundException(ex);
            }
        }
        return new ResponseEntity<AccountListResource>(new AccountListResourceAsm().toResource(accounts), HttpStatus.OK);
    }

    @RequestMapping(value="/{accountId}/dashboards", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<ReleaseDashboardListResource> findAllDashboards(@PathVariable Long accountId) {
        try {
            ReleaseDashboardList dashboardsList = service.findReleaseDashboardsByAccount(accountId);
            ReleaseDashboardListResource resource = new ReleaseDashboardListResourceAsm().toResource(dashboardsList);
            return new ResponseEntity<ReleaseDashboardListResource>(resource, HttpStatus.OK);
        } catch (AccountDoesNotExistException ex) {
            throw new NotFoundException(ex);
        }
    }
}
