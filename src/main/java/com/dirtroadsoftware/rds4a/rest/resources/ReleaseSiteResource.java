package com.dirtroadsoftware.rds4a.rest.resources;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import org.springframework.hateoas.ResourceSupport;

/**
 * Exposes {@link ReleaseSite} as a resource that can be accessed using Spring HATEOAS components.
 */
public class ReleaseSiteResource extends ResourceSupport {
    private int region;
    private int site;
    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

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

    /** Creates a {@link ReleaseSite} for this resource. */
    public ReleaseSite toReleaseSite() {
        ReleaseSite releaseSite = new ReleaseSite();
        releaseSite.setRegion(getRegion());
        releaseSite.setSite(getSite());
        return releaseSite;
    }
}
