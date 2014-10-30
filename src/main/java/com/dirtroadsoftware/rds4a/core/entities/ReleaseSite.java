package com.dirtroadsoftware.rds4a.core.entities;

/**
 * A MassDEP waste release site reported and monitored under the Massachusetts Contingency Plan.
 */
public class ReleaseSite {
    /** Internal identifier */
    private Long id;
    /** One of four geographic regions in Massachusetts */
    private int region;
    /** Site number, unique within a region */
    private int site;

    /** Get the site's region */
    public int getRegion() {
        return region;
    }

    /** Set the site's region */
    public void setRegion(int region) {
        this.region = region;
    }

    /** Get the site's number */
    public int getSite() {
        return site;
    }

    /** Set the site's number */
    public void setSite(int site) {
        this.site = site;
    }

    /** Get the internal id */
    public Long getId() {
        return id;
    }

    /** Set the internal id */
    public void setId(Long id) {
        this.id = id;
    }
}
