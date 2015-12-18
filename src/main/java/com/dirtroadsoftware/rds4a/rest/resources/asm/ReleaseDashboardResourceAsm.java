package com.dirtroadsoftware.rds4a.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.rest.mvc.AccountController;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseDashboardController;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardResource;

/**
 * Resource assembler for ReleaseDashboard. Creates a resource with the dashboard title and links to the
 * release sites associated with the dashboard and the owner, if there is one.
 */
public class ReleaseDashboardResourceAsm extends ResourceAssemblerSupport<ReleaseDashboard, ReleaseDashboardResource> {
    public ReleaseDashboardResourceAsm() {
        super(ReleaseDashboardController.class, ReleaseDashboardResource.class);
    }

    @Override
    public ReleaseDashboardResource toResource(ReleaseDashboard dashboard) {
        ReleaseDashboardResource resource = new ReleaseDashboardResource();
        resource.setTitle(dashboard.getTitle());
        resource.setRid(dashboard.getId());
        resource.add(linkTo(ReleaseDashboardController.class).slash(dashboard.getId()).withSelfRel());
        resource.add(linkTo(ReleaseDashboardController.class).slash(dashboard.getId()).slash("release-sites").withRel("release-sites"));
        if (dashboard.getOwner() != null) {
            resource.add(linkTo(AccountController.class).slash(dashboard.getOwner().getId()).withRel("owner"));
        }
        return resource;
    }
}
