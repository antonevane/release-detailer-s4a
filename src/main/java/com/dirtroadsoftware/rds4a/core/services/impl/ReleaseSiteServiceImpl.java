package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.repositories.ReleaseSiteRepository;
import com.dirtroadsoftware.rds4a.core.services.ReleaseSiteService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseSiteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 */
@Service
@Transactional
public class ReleaseSiteServiceImpl implements ReleaseSiteService {
    @Autowired
    ReleaseSiteRepository siteRepository;

    @Override
    public ReleaseSite findReleaseSite(Long id) {
        return siteRepository.findReleaseSite(id);
    }

    @Override
    public ReleaseSite deleteReleaseSite(Long id) {
        return siteRepository.deleteReleaseSite(id);
    }

    @Override
    public ReleaseSite updateReleaseSite(Long id, ReleaseSite releaseSite) {
        ReleaseSite foundSite = findReleaseSite(id);
        if (foundSite == null) {
            throw new ReleaseSiteNotFoundException();
        }
        return siteRepository.updateReleaseSite(id, releaseSite);
    }
}
