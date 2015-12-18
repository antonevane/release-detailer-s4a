package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.repositories.ReleaseDashboardRepository;
import com.dirtroadsoftware.rds4a.core.repositories.ReleaseSiteRepository;
import com.dirtroadsoftware.rds4a.core.services.ReleaseDashboardService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardNotFoundException;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseSiteList;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 */
@Service
@Transactional
public class ReleaseDashboardServiceImpl implements ReleaseDashboardService {
    @Autowired
    ReleaseDashboardRepository dashboardRepository;

    @Autowired
    ReleaseSiteRepository siteRepository;

    @Override
    public ReleaseSite createReleaseSite(Long releaseDashboardId, ReleaseSite site) {
        ReleaseDashboard dashboard = findReleaseDashboard(releaseDashboardId);
        if (dashboard == null) {
            throw new ReleaseDashboardNotFoundException();
        }
        ReleaseSite createdSite = siteRepository.save(site);
        createdSite.setDashboard(dashboard);
        return createdSite;
    }

    @Override
    public ReleaseDashboardList findAllReleaseDashboards() {
        return new ReleaseDashboardList(Lists.newArrayList(dashboardRepository.findAll()));
    }

    @Override
    public ReleaseSiteList findAllReleaseSites(Long releaseDashboardId) {
        ReleaseDashboard dashboard = findReleaseDashboard(releaseDashboardId);
        if (dashboard == null) {
            throw new ReleaseDashboardNotFoundException();
        }

        return new ReleaseSiteList(releaseDashboardId, siteRepository.findByDashboardId(releaseDashboardId));
    }

    @Override
    public ReleaseDashboard findReleaseDashboard(Long releaseDashboardId) {
        return dashboardRepository.findOne(releaseDashboardId);
    }
}
