package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;

import java.util.List;

/**
 *
 */
public interface ReleaseDashboardRepository {
    public ReleaseDashboard createReleaseDashboard(ReleaseDashboard dashboard);
    public List<ReleaseDashboard> findAllReleaseDashboards();
    public ReleaseDashboard findReleaseDashboard(Long releaseDashboardId);
    public ReleaseDashboard findReleaseDashboardByTitle(String title);
    public ReleaseDashboard findReleaseDashboardByAccount(Long accountId);
}
