package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.rest.mvc.AccountController;
import com.dirtroadsoftware.rds4a.rest.resources.AccountResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 *
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource res = new AccountResource();
        res.setName(account.getName());
        Link link = linkTo(AccountController.class).slash(account.getId()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
