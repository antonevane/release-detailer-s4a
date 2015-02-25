package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

import java.util.List;

/**
 *
 */
public class MaReleaseList {
    private List<MaRelease> releases;

    public MaReleaseList(List<MaRelease> releases) {
        this.releases = releases;
    }

    public List<MaRelease> getReleases() {
        return releases;
    }

    public void setReleases(List<MaRelease> releases) {
        this.releases = releases;
    }
}
