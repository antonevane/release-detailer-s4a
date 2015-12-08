package com.dirtroadsoftware.rds4a.core.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity based on the database view of towns, their
 * region and number of releases.
 */
@Entity
@Table(name="ma_release_town_dev")
public class MaReleaseTown {

    private String name;

    @Id
    @Column(name="zip_code")
    private String zipCode;

    /** Town latitude as pulled from Google Map API */
    @Column(name="latitude")
    private Double townLatitude;

    /** Town longitude as pulled from Google Map API */
    @Column(name="longitude")
    private Double townLongitude;

    private long releases;
    private int region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getTownLatitude() {
        return townLatitude;
    }

    public void setTownLatitude(Double townLatitude) {
        this.townLatitude = townLatitude;
    }

    public Double getTownLongitude() {
        return townLongitude;
    }

    public void setTownLongitude(Double townLongitude) {
        this.townLongitude = townLongitude;
    }

    public long getReleases() {
        return releases;
    }

    public void setReleases(long releases) {
        this.releases = releases;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }
}
