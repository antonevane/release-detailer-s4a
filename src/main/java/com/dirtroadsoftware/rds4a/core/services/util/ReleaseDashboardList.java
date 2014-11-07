package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ReleaseDashboardList {
    private List<ReleaseDashboard> dashboards = new ArrayList<ReleaseDashboard>();

    public ReleaseDashboardList(List<ReleaseDashboard> dashboards) {
        this.dashboards = dashboards;
    }

    public List<ReleaseDashboard> getDashboards() {return dashboards;}

    public void setDashboards(List<ReleaseDashboard> dashboards) {this.dashboards = dashboards;}
}
