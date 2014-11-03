package com.dirtroadsoftware.rds4a.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ReleaseDashboardListResource extends ResourceSupport {
    private List<ReleaseDashboardResource> dashboards = new ArrayList<ReleaseDashboardResource>();

    public List<ReleaseDashboardResource> getDashboards() {
        return dashboards;
    }

    public void setDashboards(List<ReleaseDashboardResource> dashboards) {
        this.dashboards = dashboards;
    }
}
