package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;

import java.util.List;

/**
 *
 */
public interface MaReleaseTownRepository {
    List<MaReleaseTown> findTownsWithReleasesByRegion(int region);
    List<MaReleaseTown> findAllTownsWithReleases();

    MaReleaseTown findTownByZipCode(String zipCode);
}
