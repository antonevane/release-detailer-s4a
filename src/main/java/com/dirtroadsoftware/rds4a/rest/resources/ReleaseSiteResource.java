package com.dirtroadsoftware.rds4a.rest.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 *
 */
public class ReleaseSiteResource extends ResourceSupport {
    private int region;
    private int site;

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }
}
