package com.dirtroadsoftware.rds4a.rest.resources;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import org.springframework.hateoas.ResourceSupport;

/**
 * Exposes {@link com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite} as a resource that can be accessed using Spring HATEOAS components.
 */
public class MaReleaseResource extends ResourceSupport {
    private int region;
    private int site;
    private String siteName;
    private String address;
    private Long rid;
    private String rtn;

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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    /** Creates a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} for this resource. */
    public MaRelease toMaRelease() {
        MaRelease maRelease = new MaRelease();
        maRelease.setRegion(getRegion());
        maRelease.setSite(getSite());
        maRelease.setSiteName(getSiteName());
        maRelease.setAddress(getAddress());
        maRelease.setRtn(getRtn());
        return maRelease;
    }
}
