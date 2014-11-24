package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of release sites and the ID of the town in which they are found.
 */
public class TownMaReleaseList {
    private List<MaRelease> releases = new ArrayList<MaRelease>();
    private Long townId;

    public TownMaReleaseList(Long townId, List<MaRelease> releases) {
        this.townId = townId;
        this.releases = releases;
    }

    public List<MaRelease> getReleases() {return releases;}

    public void setReleases(List<MaRelease> releases) {this.releases = releases;}

    public Long getTownId() {return townId;}

    public void setTownId(Long townId) {this.townId = townId;}
}
