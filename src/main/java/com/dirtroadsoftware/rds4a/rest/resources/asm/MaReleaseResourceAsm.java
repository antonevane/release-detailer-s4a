package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseController;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseSiteController;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Assembles {@link com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource} from {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease}.
 */
public class MaReleaseResourceAsm extends ResourceAssemblerSupport<MaRelease, MaReleaseResource> {

    public MaReleaseResourceAsm() {
        super(MaReleaseController.class, MaReleaseResource.class);
    }

    @Override
    public MaReleaseResource toResource(MaRelease site) {
        MaReleaseResource res = new MaReleaseResource();
        res.setRegion(site.getRegion());
        res.setSite(site.getSite());
        res.setSiteName(site.getSiteName());
        res.setAddress(site.getAddress());
        res.setRid(site.getId());
        res.setRtn(site.getRtn());
        Link link = linkTo(MaReleaseController.class).slash(site.getId()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
