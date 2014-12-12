package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

import java.util.List;

/**
 *
 */
public interface MaActionRepository {
    public List<MaAction> findActionsByMaRelease(MaRelease release);
    public MaAction findMaAction(long actionId);
}
