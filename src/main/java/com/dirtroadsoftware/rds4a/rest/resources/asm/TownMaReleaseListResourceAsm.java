package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.services.util.TownMaReleaseList;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseController;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseDashboardController;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseListResource;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assemble a {@link com.dirtroadsoftware.rds4a.rest.resources.MaReleaseListResource} from a
 * {@link com.dirtroadsoftware.rds4a.core.services.util.TownMaReleaseList} associated with a town
 */
public class TownMaReleaseListResourceAsm extends ResourceAssemblerSupport<TownMaReleaseList, MaReleaseListResource> {
    public TownMaReleaseListResourceAsm() {
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
    public MaReleaseListResource toResource(TownMaReleaseList list) {
        List<MaReleaseResource> resources = new MaReleaseResourceAsm().toResources(list.getReleases());
        MaReleaseListResource listResource = new MaReleaseListResource();
        listResource.setReleases(resources);
        listResource.add(linkTo(methodOn(MaReleaseController.class).findMaReleasesByTown(list.getTownId())).withSelfRel());
        return listResource;
    }
}
