package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.services.util.AccountList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;

/**
 * Service for finding and creating an {@link Account} and create a {@link ReleaseDashboard}.
 */
public interface AccountService {

    /**
     * Finds the {@link Account} with the given id
     *
     * @param id the id of the {@link Account} to lookup
     * @return the {@link Account} if it exists, otherwise null
     */
	public Account findAccount(Long id);

	public Account createAccount(Account account);

	public ReleaseDashboard createReleaseDashboard(Long accountId, ReleaseDashboard dashboard);

	public ReleaseDashboardList findReleaseDashboardsByAccount(Long accountId);

	public AccountList findAllAccounts();

	public Account findByAccountName(String name);
}
