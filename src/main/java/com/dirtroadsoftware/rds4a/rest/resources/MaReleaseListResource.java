package com.dirtroadsoftware.rds4a.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Resource for the list of MA releases
 */
public class MaReleaseListResource extends ResourceSupport {
    private List<MaReleaseResource> releases;

    public List<MaReleaseResource> getReleases() {
        return releases;
    }

    public void setReleases(List<MaReleaseResource> releases) {
        this.releases = releases;
    }
}
