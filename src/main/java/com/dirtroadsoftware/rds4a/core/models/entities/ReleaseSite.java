package com.dirtroadsoftware.rds4a.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * A MassDEP waste release site reported and monitored under the Massachusetts Contingency Plan.
 */
@Entity
public class ReleaseSite {
    /** Internal identifier */
    @Id @GeneratedValue
    private Long id;
    /** One of four geographic regions in Massachusetts */
    private int region;
    /** Site number, unique within a region */
    private int site;
    /**
     * TODO
     * Temporary relationship - ReleaseSite to ReleaseDashboard -- that will
     * be changed in a future version.
     */
    @OneToOne
    private ReleaseDashboard dashboard;


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

    public ReleaseDashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(ReleaseDashboard dashboard) {
        this.dashboard = dashboard;
    }
}
