package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.services.AccountService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountDoesNotExistException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountExistsException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardExistsException;
import com.dirtroadsoftware.rds4a.rest.exceptions.BadRequestException;
import com.dirtroadsoftware.rds4a.rest.exceptions.ConflictException;
import com.dirtroadsoftware.rds4a.rest.exceptions.NotFoundException;
import com.dirtroadsoftware.rds4a.rest.resources.AccountResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.AccountResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseDashboardResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;


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
    public ResponseEntity<ReleaseDashboardResource> createReleaseDashboard(@PathVariable Long accountId,
                                                                           @RequestBody ReleaseDashboardResource sentDashboard) {
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
    }
}
