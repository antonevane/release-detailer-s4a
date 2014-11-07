package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;

import java.util.List;

/**
 *
 */
public interface ReleaseDashboardRepository {
    public ReleaseDashboard createReleaseDashboard(ReleaseDashboard dashboard);
    public List<ReleaseDashboard> findAllReleaseDashboards();
    public ReleaseDashboard findReleaseDashboard(Long releaseDashboardId);
    public ReleaseDashboard findReleaseDashboardByTitle(String title);
    public ReleaseDashboardList findReleaseDashboardsByAccount(Long accountId);
}
