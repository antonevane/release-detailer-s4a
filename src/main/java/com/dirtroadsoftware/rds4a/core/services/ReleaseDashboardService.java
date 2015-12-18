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
     * Create a Release Site.
     * @param releaseDashboardId
     * @param site
     * @return
     */
    public ReleaseSite createReleaseSite(Long releaseDashboardId, ReleaseSite site);

    /**
     * Find all release dashboards.
     * @return
     */
    public ReleaseDashboardList findAllReleaseDashboards();

    /**
     * Find all release sites for a dashboard.
     * @param releaseDashboardId
     * @return
     */
    public ReleaseSiteList findAllReleaseSites(Long releaseDashboardId);

    /**
     * Finds the {@link ReleaseDashboard} with the given id
     *
     * @param releaseDashboardId the id of the {@link ReleaseDashboard} to lookup
     * @return the {@link ReleaseDashboard} if it exists, otherwise null
     */
    public ReleaseDashboard findReleaseDashboard(Long releaseDashboardId);

}
