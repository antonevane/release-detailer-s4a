package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.services.MaActionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class MaActionServiceImpl implements MaActionService {
    @Override
    public List<MaAction> findActionsByReleaseId(Long releaseId) {
        throw new UnsupportedOperationException();
    }
}
