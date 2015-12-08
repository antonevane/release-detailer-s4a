package com.dirtroadsoftware.rds4a.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MaReleaseTownListResource extends ResourceSupport {
    private List<MaReleaseTownResource> towns = new ArrayList<MaReleaseTownResource>();

    public List<MaReleaseTownResource> getTowns() {
        return towns;
    }

    public void setTowns(List<MaReleaseTownResource> towns) {
        this.towns = towns;
    }
}
