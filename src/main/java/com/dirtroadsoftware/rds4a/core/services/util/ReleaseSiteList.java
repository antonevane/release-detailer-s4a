package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of release sites and the ID of the dashboard to which they belong.
 */
public class ReleaseSiteList {
    private List<ReleaseSite> sites = new ArrayList<ReleaseSite>();
    private Long releaseDashboardId;

    public List<ReleaseSite> getSites() {return sites;}

    public void setSites(List<ReleaseSite> sites) {this.sites = sites;}

    public Long getReleaseDashboardId() {return releaseDashboardId;}

    public void setReleaseSiteId(Long releaseDashboardId) {this.releaseDashboardId = releaseDashboardId;}
}
