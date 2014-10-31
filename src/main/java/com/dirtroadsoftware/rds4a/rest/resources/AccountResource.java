package com.dirtroadsoftware.rds4a.rest.resources;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 */
public class AccountResource extends ResourceSupport {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Creates a {@link Account} for this resource. */
    public Account toAccount() {
        Account account = new Account();
        account.setName(getName());
        return account;
    }
}
