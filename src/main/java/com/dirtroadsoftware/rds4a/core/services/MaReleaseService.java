package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseList;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface MaReleaseService {
    public MaRelease findMaRelease(Long id);
    public MaRelease findMaReleaseByRtn(String rtn);

    /**
     * Fetch the MaRelease with the given ID and load the release's actions
     */
    public MaRelease findMaReleaseWithActionsById(Long id);

    public MaRelease findMaReleaseWithActionsByRtn(String rtn);

    public MaReleaseList findMaReleasesByTown(String town);

    public MaReleaseList findMaReleasesByTown(String town, String sortBy, String sortHow, int offset, int limit);

    public Long countMaReleasesByTown(String town);

}
