package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.services.util.MaActionList;
import com.dirtroadsoftware.rds4a.rest.mvc.MaActionController;
import com.dirtroadsoftware.rds4a.rest.resources.MaActionListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 */
public class MaActionListResourceAsm extends ResourceAssemblerSupport<MaActionList, MaActionListResource> {
    public MaActionListResourceAsm() {
        super(MaActionController.class, MaActionListResource.class);
    }

    @Override
    public MaActionListResource toResource(MaActionList maActionList) {
        MaActionListResource resource = new MaActionListResource();
        resource.setActions(new MaActionResourceAsm().toResources(maActionList.getActions()));
        return resource;
    }
}
