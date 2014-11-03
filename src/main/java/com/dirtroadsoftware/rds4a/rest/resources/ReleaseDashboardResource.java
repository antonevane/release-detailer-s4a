package com.dirtroadsoftware.rds4a.rest.resources;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 */
public class ReleaseDashboardResource extends ResourceSupport {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /** Creates a {@link ReleaseDashboard} for this resource. */
    public ReleaseDashboard toReleaseDashboard() {
        ReleaseDashboard dashboard= new ReleaseDashboard();
        dashboard.setTitle(getTitle());
        return dashboard;
    }
}
