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
    public List<MaAction> findMaActionsByTown(String town, String sortBy, String sortHow, int offset, int limit);
    public List<MaAction> findMaActionsByDate(String date, String sortBy, String sortHow, int offset, int limit);
    public Long countMaActions(String actionAttribute, String attributeValue);
}
