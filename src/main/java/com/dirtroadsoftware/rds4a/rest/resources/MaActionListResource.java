package com.dirtroadsoftware.rds4a.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MaActionListResource extends ResourceSupport {
    private List<MaActionResource> actions = new ArrayList<MaActionResource>();

    public List<MaActionResource> getActions() {
        return actions;
    }

    public void setActions(List<MaActionResource> actions) {
        this.actions = actions;
    }

}
