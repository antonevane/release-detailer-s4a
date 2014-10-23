package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseSiteController;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assembles {@link ReleaseSiteResource} from {@link ReleaseSite}.
 */
public class ReleaseSiteResourceAsm extends ResourceAssemblerSupport<ReleaseSite, ReleaseSiteResource> {

    public ReleaseSiteResourceAsm() {
        super(ReleaseSiteController.class, ReleaseSiteResource.class);
    }

    @Override
    public ReleaseSiteResource toResource(ReleaseSite site) {
        ReleaseSiteResource res = new ReleaseSiteResource();
        res.setRegion(site.getRegion());
        res.setSite(site.getSite());
        Link link = linkTo(ReleaseSiteController.class).slash(site.getId()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
