package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseController;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assembles {@link com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource} from {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease}.
 */
public class MaReleaseResourceAsm extends ResourceAssemblerSupport<MaRelease, MaReleaseResource> {

    public MaReleaseResourceAsm() {
        super(MaReleaseController.class, MaReleaseResource.class);
    }

    @Override
    public MaReleaseResource toResource(MaRelease release) {
        MaReleaseResource res = new MaReleaseResource();

        // Identifiers
        res.setRid(release.getId());
        res.setRtn(release.getRtn());
        res.setRegion(release.getRegion());
        res.setSite(release.getSite());

        // Name and location
        res.setSiteName(release.getSiteName());
        res.setAddress(release.getAddress());
        res.setTown(release.getTown());
        res.setZipCode(release.getZipCode());
        // TODO location types

        // Sources
        // TODO release sources

        // Status and actions
        res.setActive(release.getActive());
        res.setRaoClass(release.getRaoClass());
        res.setPhase(release.getPhase());

        res.setNumActions(release.getNumActions());

        // Dates
        res.setComplianceStatusDate(release.getStatusDate());
        res.setNotificationDate(release.getNotification());

        Link link = linkTo(MaReleaseController.class).slash(release.getId()).withSelfRel();
        res.add(link);

        Link actionsLink = linkTo(methodOn(MaReleaseController.class).findAllActions(release.getId())).withRel("actions");
        res.add(actionsLink);

        return res;
    }
}
