package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ReleaseSiteList {
    private List<ReleaseSite> entries = new ArrayList<ReleaseSite>();
    private Long releaseDashboardId;

    public List<ReleaseSite> getEntries() {return entries;}

    public void setEntries(List<ReleaseSite> entries) {this.entries = entries;}

    public Long getReleaseDashboardId() {return releaseDashboardId;}

    public void setReleaseSiteId(Long releaseDashboardId) {this.releaseDashboardId = releaseDashboardId;}
}
