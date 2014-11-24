package com.dirtroadsoftware.rds4a.core.services.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.StringUtils;

import java.text.ParseException;

/**
 * Helper class for parsing and formatting RTNs
 */
public class Rtn {
    private static final int RTN_SEPARATOR = '-';
    private int region;
    private int site;
    private String str;

    public Rtn(int region, int site) {
        if (region < 0 || region > 4) {
            throw new IllegalArgumentException("region " + region + " is not between 1 and 4, inclusive");
        } else if (site < 0) {
            throw new IllegalArgumentException("site " + site + " must be greater than zero");
        }
        this.region = region;
        this.site = site;
        str = format(region, site);
    }


    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (!(obj instanceof Rtn)) {return false;}

        Rtn that = (Rtn) obj;
        return new EqualsBuilder()
                .append(this.region, that.region)
                .append(this.site, that.site)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 71)
                .append(this.region)
                .append(this.site)
                .toHashCode();
    }

    public static String format(int region, int site) {
        return String.format("%02d-%07d", region, site);
    }

    public static Rtn parseRtn(String rtnString) throws ParseException {
        if (rtnString == null) {
            throw new NullPointerException("rtnString is null");
        } else {
            int sepIndex = rtnString.indexOf(RTN_SEPARATOR);
            if (sepIndex <= 0) {
                throw new ParseException(rtnString, sepIndex);
            }
            String regionString = rtnString.substring(0, sepIndex);
            String siteString = rtnString.substring(sepIndex + 1);
            int region = Integer.parseInt(regionString);
            int site = Integer.parseInt(siteString);
            return new Rtn(region, site);
        }
    }
}