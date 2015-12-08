package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;

import java.util.List;

/**
 *
 */
public class MaReleaseTownList {
    private List<MaReleaseTown> towns;

    public MaReleaseTownList(List<MaReleaseTown> towns) {
        this.towns = towns;
    }

    public List<MaReleaseTown> getTowns() {
        return towns;
    }

    public void setTowns(List<MaReleaseTown> towns) {
        this.towns = towns;
    }
}
