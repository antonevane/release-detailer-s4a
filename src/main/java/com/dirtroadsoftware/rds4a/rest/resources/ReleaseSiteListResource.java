package com.dirtroadsoftware.rds4a.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Resource for the list of release sites
 */
public class ReleaseSiteListResource extends ResourceSupport {
    private List<ReleaseSiteResource> sites;

    public List<ReleaseSiteResource> getSites() {
        return sites;
    }

    public void setSites(List<ReleaseSiteResource> sites) {
        this.sites = sites;
    }
}
