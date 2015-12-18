package com.dirtroadsoftware.rds4a.rest.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

/**
 * Exposes {@link com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite} as a resource that can be accessed using Spring HATEOAS components.
 */
public class MaReleaseResource extends ResourceSupport {
    private int region;
    private int site;
    private String siteName;
    private String address;
    private String town;
    private Long rid;
    private String rtn;
    private String zipCode;
    private Boolean active;
    private String raoClass;
    private Integer phase;
    private String complianceStatusDate;
    private String notificationDate;
    private int numActions;
    private String locations;
    private int numSources;
    private String sources;
    private String chemicals;
    private String category;

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    /** Creates a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} for this resource. */
    public MaRelease toMaRelease() {
        MaRelease maRelease = new MaRelease();
        maRelease.setRegion(getRegion());
        maRelease.setSite(getSite());
        maRelease.setSiteName(getSiteName());
        maRelease.setAddress(getAddress());
        maRelease.setTown(getTown());
        maRelease.setRtn(getRtn());
        return maRelease;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setRaoClass(String raoClass) {
        this.raoClass = raoClass;
    }

    public String getRaoClass() {
        return raoClass;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setComplianceStatusDate(Date complianceStatusDate) {
        this.complianceStatusDate = DATE_FORMAT.get().format(complianceStatusDate);
    }

    public String getComplianceStatusDate() {
        return complianceStatusDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = DATE_FORMAT.get().format(notificationDate);
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNumActions(int numActions) {
        this.numActions = numActions;
    }

    public int getNumActions() {
        return numActions;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public void setNumSources(int numSources) {
        this.numSources = numSources;
    }

    public int getNumSources() {
        return numSources;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getChemicals() {
        return chemicals;
    }

    public void setChemicals(String chemicals) {
        this.chemicals = chemicals;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
