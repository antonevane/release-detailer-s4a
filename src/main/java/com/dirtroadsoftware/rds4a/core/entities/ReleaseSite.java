package com.dirtroadsoftware.rds4a.core.entities;

/**
 *
 */
public class ReleaseSite {
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
