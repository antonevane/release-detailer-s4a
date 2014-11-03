package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseDashboardController;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardListResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class ReleaseDashboardListResourceAsm extends ResourceAssemblerSupport<ReleaseDashboardList, ReleaseDashboardListResource> {
    public ReleaseDashboardListResourceAsm() {
        super(ReleaseDashboardController.class, ReleaseDashboardListResource.class);
    }

    @Override
    public ReleaseDashboardListResource toResource(ReleaseDashboardList dashboardList) {
        ReleaseDashboardListResource resource = new ReleaseDashboardListResource();
        resource.setDashboards(new ReleaseDashboardResourceAsm().toResources(dashboardList.getDashboards()));
        return resource;
    }
}
