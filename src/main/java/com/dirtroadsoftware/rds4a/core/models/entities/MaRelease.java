package com.dirtroadsoftware.rds4a.core.models.entities;

import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * A MassDEP waste release site reported and monitored under the Massachusetts Contingency Plan.
 *
 * For definitions of terminology, see the MassDEP web page:
 *     http://www.mass.gov/eea/agencies/massdep/cleanup/sites/definitions-of-fields-listed-in-search-result.html
 * and
 *     http://www.mass.gov/eea/agencies/massdep/cleanup/regulations/site-rtns-and-status.html
 */
@Entity
@Table(name="ma_release_dev")
public class MaRelease {

    @Id
    private Long id;

    /** One of four geographic regions in Massachusetts */
    private int region;
    /** Site number, unique within a region */
    private int site;
    /** Release tracking number, assigned by MassDEP */
    private String rtn;

    /** Name given to release site or location aid */
    @Column(name="site_name")
    private String siteName;
    /** Address of release, street address or other descriptive text */
    private String address;
    /** Town where release is located, not at the neighborhood level sometimes reported to MassDEP */
    private String town;
    /** Zip code of town where release is located */
    @Column(name="zip_code")
    private String zipCode;
    /** Date MassDEP was notified of the release. */
    private Date notification;
    /** How quickly the release needed to be reported to MassDEP: 2 hours, 72 hours or 120 days */
    private String category;
    /** Compliance status */
    private String status;
    /** Type of matter released: Oil (O), Hazardous Material (HM), or Oil and Hazardous Material (OHM)  */
    private String ohm;
    /** Cleanup Phase */
    private Integer phase;
    /** Class of Response Action Outcome (RAO) */
    @Column(name="rao_class")
    private String raoClass;
    /** The site is being not closed and future remediation, monitoring or reporting is expected. */
    private Boolean active;
    /** Compliance status date (Current Date on MassDEP site) */
    @Column(name="status_date")
    private Date statusDate;
    /** Town latitude as pulled from Google Map API */
    @Column(name="town_latitude")
    private Double townLatitude;
    /** Town longitude as pulled from Google Map API */
    @Column(name="town_longitude")
    private Double townLongitude;

    private String location;

    @OneToMany(mappedBy = "release", fetch = FetchType.EAGER)
    @OrderBy("source")
    private List<MaSource> sources;

    @OneToMany(mappedBy = "release", fetch = FetchType.LAZY)
    @OrderBy("date ASC")
    private List<MaAction> actions;

    @OneToMany(mappedBy = "release", fetch = FetchType.EAGER)
    @OrderBy("chemical ASC")
    private List<MaChemical> chemicals;

    @Formula("(SELECT COUNT(a.id) FROM ma_action_dev a WHERE a.release_id = id)")
    private int numActions;

    @Formula("(SELECT COUNT(s.id) FROM ma_source_dev s WHERE s.release_id = id)")
    private int numSources;

    // TODO add numChemicals
//    @Formula("(SELECT COUNT(c.id) FROM ma_chemical_dev c WHERE c.release_id = id)")
//    private int numChemicals;

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

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getNotification() {
        return notification;
    }

    public void setNotification(Date notification) {
        this.notification = notification;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOhm() {
        return ohm;
    }

    public void setOhm(String ohm) {
        this.ohm = ohm;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public String getRaoClass() {
        return raoClass;
    }

    public void setRaoClass(String raoClass) {
        this.raoClass = raoClass;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
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

    public List<MaAction> getActions() {
        return actions;
    }

    public void setActions(List<MaAction> actions) {
        this.actions = actions;
    }

    public int getNumActions() {
        return numActions;
    }

    public void setNumActions(int numActions) {
        this.numActions = numActions;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumSources() {
        return numSources;
    }

    public List<MaSource> getSources() {
        return sources;
    }

    public List<MaChemical> getChemicals() {
        return chemicals;
    }

    public void setChemicals(List<MaChemical> chemicals) {
        this.chemicals = chemicals;
    }
}
