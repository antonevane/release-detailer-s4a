package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.services.AccountService;
import com.dirtroadsoftware.rds4a.rest.resources.AccountResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.AccountResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping(value = "/rest/accounts")
public class AccountController {
    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @RequestMapping(value="/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> findAccount(@PathVariable Long accountId) {
        Account account = service.findAccount(accountId);
        if (account != null) {
            AccountResourceAsm asm = new AccountResourceAsm();
            AccountResource res = asm.toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

}
