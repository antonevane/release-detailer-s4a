package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseTownList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseTownController;
import com.dirtroadsoftware.rds4a.rest.mvc.ReleaseDashboardController;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseTownListResource;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseTownResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 */
public class MaReleaseTownListResourceAsm extends ResourceAssemblerSupport<MaReleaseTownList, MaReleaseTownListResource> {
    public MaReleaseTownListResourceAsm() {
        super(MaReleaseTownController.class, MaReleaseTownListResource.class);
    }

    @Override
    public MaReleaseTownListResource toResource(MaReleaseTownList townList) {
        MaReleaseTownListResource resource = new MaReleaseTownListResource();
        resource.setTowns(new MaReleaseTownResourceAsm().toResources(townList.getTowns()));
        return resource;
    }
}
