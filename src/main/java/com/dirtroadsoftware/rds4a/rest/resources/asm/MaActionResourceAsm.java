package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.rest.mvc.MaActionController;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseController;
import com.dirtroadsoftware.rds4a.rest.resources.MaActionResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 */
public class MaActionResourceAsm extends ResourceAssemblerSupport<MaAction, MaActionResource> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public MaActionResourceAsm() {
        super(MaActionController.class, MaActionResource.class);
    }

    @Override
    public MaActionResource toResource(MaAction action) {
        MaActionResource res = new MaActionResource();

        res.setRid(action.getId());
        res.setAction(action.getAction());
        res.setDate(DATE_FORMAT.format(action.getDate()));
        res.setRtn(action.getRtn());
        res.setStatus(action.getStatus());

        Link selfLink = linkTo(MaActionController.class).slash(action.getId()).withSelfRel();
        res.add(selfLink);

        // Link to release
        Link releaseLink = linkTo(methodOn(MaReleaseController.class).getMaReleaseByRtn(action.getRtn())).withRel("release");
        res.add(releaseLink);

        return res;
    }
}
