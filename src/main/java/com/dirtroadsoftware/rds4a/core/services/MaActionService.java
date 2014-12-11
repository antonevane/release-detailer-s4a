package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;

import java.util.List;

/**
 *
 */
public interface MaActionService {
    List<MaAction> findActionsByReleaseId(Long releaseId);
}
