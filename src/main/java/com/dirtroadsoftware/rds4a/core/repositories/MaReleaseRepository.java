package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

import java.util.List;

/**
 *
 */
public interface MaReleaseRepository {
    public MaRelease findMaRelease(Long id);
    public List<MaRelease> findMaReleasesByTown(String town);
    public MaRelease findMaReleaseByRegionSite(int region, int site);
    public List<String> findTownsWithReleasesByRegion(int region);
}
