package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseSiteList;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseController;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseDashboardController;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseListResource;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteListResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assemble a {@link com.dirtroadsoftware.rds4a.rest.resources.MaReleaseListResource} from
 * a {@link com.dirtroadsoftware.rds4a.core.services.util.MaReleaseList}
 */
public class MaReleaseListResourceAsm extends ResourceAssemblerSupport<MaReleaseList, MaReleaseListResource> {
    public MaReleaseListResourceAsm() {
        super(ReleaseDashboardController.class, MaReleaseListResource.class);
    }

    /**
     * Get a list of MaReleaseResources, using the MaReleaseResource assembler, so it can be
     * added to the MaReleaseListResource.
     *
     * @param list list of release sites used to build a resource
     * @return the resource
     */
    @Override
    public MaReleaseListResource toResource(MaReleaseList list) {
        List<MaReleaseResource> resources = new MaReleaseResourceAsm().toResources(list.getReleases());
        MaReleaseListResource listResource = new MaReleaseListResource();
        listResource.setReleases(resources);
        return listResource;
    }

    public MaReleaseListResource toResource(MaReleaseList list, String town, int offset, int limit, int totalPages) {
        List<MaReleaseResource> resources = new MaReleaseResourceAsm().toResources(list.getReleases());
        MaReleaseListResource listResource = new MaReleaseListResource();
        listResource.setReleases(resources);

        if (offset > 0) {
            Link previousLink = createPreviousLink(town, offset, limit);
            listResource.add(previousLink);
        }
        if (offset + 1 < totalPages) {
            Link nextLink = createNextLink(town, offset, limit);
            listResource.add(nextLink);
        }
        return listResource;
    }

    private Link createPreviousLink(String town, int offset, int limit) {
        offset = offset - 1;
        Link previousLink = linkTo(methodOn(MaReleaseController.class).findReleasesByTown(town, offset, limit)).withRel(Link.REL_PREVIOUS);
        return previousLink;
    }

    private Link createNextLink(String town, int offset, int limit) {
        offset = offset + 1;
        Link nextLink = linkTo(methodOn(MaReleaseController.class).findReleasesByTown(town, offset, limit)).withRel(Link.REL_NEXT);
        return nextLink;
    }
}
