package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.services.util.AccountList;
import com.dirtroadsoftware.rds4a.rest.mvc.AccountController;
import com.dirtroadsoftware.rds4a.rest.resources.AccountListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource>{

    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        AccountListResource resource = new AccountListResource();
        resource.setAccounts(new AccountResourceAsm().toResources(accountList.getAccounts()));
        return resource;
    }
}
