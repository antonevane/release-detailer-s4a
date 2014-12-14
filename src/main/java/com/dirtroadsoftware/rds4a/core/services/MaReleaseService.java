package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

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
}
