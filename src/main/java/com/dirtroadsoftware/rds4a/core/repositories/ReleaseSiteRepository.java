package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;

import java.util.List;

/**
 *
 */
public interface ReleaseSiteRepository {
    public ReleaseSite findReleaseSite(Long id);
    public ReleaseSite deleteReleaseSite(Long id);
    public ReleaseSite updateReleaseSite(Long id, ReleaseSite releaseSite);
    public ReleaseSite createReleaseSite(ReleaseSite data);
    public List<ReleaseSite> findByReleaseDashboardId(Long releaseDashboardId);
}
