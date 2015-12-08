package com.dirtroadsoftware.rds4a.rest.resources;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;
import org.springframework.hateoas.ResourceSupport;

/**
 * Exposes {@link MaReleaseTown} as a resource that can be accessed using Spring HATEOAS components.
 */
public class MaReleaseTownResource extends ResourceSupport {
    private String name;
    private int region;
    private long numReleases;
    private String zipCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public long getNumReleases() {
        return numReleases;
    }

    public void setNumReleases(long numReleases) {
        this.numReleases = numReleases;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }
}
