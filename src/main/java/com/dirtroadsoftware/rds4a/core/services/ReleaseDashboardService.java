package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseSiteList;

/**
 * Service for finding a {@link ReleaseDashboard}, creating a {@link ReleaseSite} and finding
 * dashboards and sites.
 */
public interface ReleaseDashboardService {
    /**
     * Finds the {@link ReleaseDashboard} with the given id
     *
     * @param id the id of the {@link ReleaseDashboard} to lookup
     * @return the {@link ReleaseDashboard} if it exists, otherwise null
     */
    public ReleaseDashboard findReleaseDashboard(Long id);

    public ReleaseSite createReleaseSite(Long siteId, ReleaseSite site);
    public ReleaseSiteList findAllReleaseSites();
    public ReleaseDashboardList findAllReleaseDashboards();
}
