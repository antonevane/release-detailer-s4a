package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseTownController;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseTownResource;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 */
public class MaReleaseTownResourceAsm extends ResourceAssemblerSupport<MaReleaseTown, MaReleaseTownResource> {
    public MaReleaseTownResourceAsm() {
        super(MaReleaseTownController.class, MaReleaseTownResource.class);
    }

    @Override
    public MaReleaseTownResource toResource(MaReleaseTown town) {
        MaReleaseTownResource res = new MaReleaseTownResource();
        res.setName(capitalize(town.getName()));
        res.setNumReleases(town.getReleases());
        res.setRegion(town.getRegion());
        res.setZipCode(town.getZipCode());

        res.add(linkTo(methodOn(MaReleaseTownController.class).getMaReleaseTown(town.getZipCode())).withSelfRel());

        return res;
    }

    private String capitalize(String name) {
        return WordUtils.capitalizeFully(name);
    }
}
