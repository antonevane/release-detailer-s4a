package com.dirtroadsoftware.rds4a.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.rest.mvc.AccountController;
import com.dirtroadsoftware.rds4a.rest.resources.AccountResource;

/**
 *
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource resource = new AccountResource();
        resource.setName(account.getName());
        resource.setPassword(account.getPassword());
        resource.setRid(account.getId());
        resource.add(linkTo(AccountController.class).slash(account.getId()).withSelfRel());
        resource.add(linkTo(methodOn(AccountController.class).findAllDashboards(account.getId())).withRel("dashboards"));
        return resource;
    }
}
