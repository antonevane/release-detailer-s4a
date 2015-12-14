package com.dirtroadsoftware.rds4a.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.repositories.MaActionRepository;
import com.dirtroadsoftware.rds4a.core.services.MaActionService;

/**
 *
 */
@Service
@Transactional
public class MaActionServiceImpl implements MaActionService {
    @Autowired
    MaActionRepository repository;

    @Override
    public MaAction findActionById(Long actionId) {
        return repository.findOne(actionId);
    }
}