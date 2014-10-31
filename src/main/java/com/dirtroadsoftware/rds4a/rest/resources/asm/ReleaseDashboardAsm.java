package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseDashboardController;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 *
 */
public class ReleaseDashboardAsm extends ResourceAssemblerSupport<ReleaseDashboard, ReleaseDashboardResource> {
    public ReleaseDashboardAsm() {
        super(ReleaseDashboardController.class, ReleaseDashboardResource.class);
    }

    @Override
    public ReleaseDashboardResource toResource(ReleaseDashboard dashboard) {
        ReleaseDashboardResource res = new ReleaseDashboardResource();
        res.setTitle(dashboard.getTitle());
        Link link = linkTo(ReleaseDashboardController.class).slash(dashboard.getId()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
