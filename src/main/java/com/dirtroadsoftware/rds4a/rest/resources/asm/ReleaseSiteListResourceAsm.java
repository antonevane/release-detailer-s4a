package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.services.util.ReleaseSiteList;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseDashboardController;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteListResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assemble a {@link ReleaseSiteListResource} from a {@link ReleaseSiteList}
 */
public class ReleaseSiteListResourceAsm extends ResourceAssemblerSupport<ReleaseSiteList, ReleaseSiteListResource> {
    public ReleaseSiteListResourceAsm() {
        super(ReleaseDashboardController.class, ReleaseSiteListResource.class);
    }

    /**
     * Get a list of ReleaseSiteResources, using the ReleaseSiteResource assembler, so it can be
     * added to the ReleaseSiteListResource.
     *
     * @param list list of release sites used to build a resource
     * @return the resource
     */
    @Override
    public ReleaseSiteListResource toResource(ReleaseSiteList list) {
        List<ReleaseSiteResource> resources = new ReleaseSiteResourceAsm().toResources(list.getSites());
        ReleaseSiteListResource listResource = new ReleaseSiteListResource();
        listResource.setSites(resources);
        listResource.add(linkTo(methodOn(ReleaseDashboardController.class).findAllReleaseSites(list.getReleaseDashboardId())).withSelfRel());
        return listResource;
    }
}
