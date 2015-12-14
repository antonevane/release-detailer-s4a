package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.repositories.ReleaseDashboardRepository;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 */
public class JpaReleaseDashboardRepository {
    @PersistenceContext
    private EntityManager em;

    public ReleaseDashboard createReleaseDashboard(ReleaseDashboard dashboard) {
        em.persist(dashboard);
        return dashboard;
    }

    @SuppressWarnings("unchecked")
    public List<ReleaseDashboard> findAllReleaseDashboards() {
        Query query = em.createQuery("SELECT rd FROM ReleaseDashboard rd");
        List<ReleaseDashboard> releaseDashboards = query.getResultList();
        return releaseDashboards;
    }

    public ReleaseDashboard findReleaseDashboard(Long releaseDashboardId) {
        return em.find(ReleaseDashboard.class, releaseDashboardId);
    }

    @SuppressWarnings("unchecked")
    public ReleaseDashboard findReleaseDashboardByTitle(String title) {
        Query query = em.createQuery("SELECT rd FROM ReleaseDashboard rd WHERE rd.title=?1");
        query.setParameter(1, title);
        List<ReleaseDashboard> dashboards = query.getResultList();
        if (dashboards.isEmpty()) {
            return null;
        } else {
            return dashboards.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ReleaseDashboard> findReleaseDashboardsByAccount(Long accountId) {
        Query query = em.createQuery("SELECT rd FROM ReleaseDashboard rd WHERE rd.owner.id=?1");
        query.setParameter(1, accountId);
        List<ReleaseDashboard> dashboards = query.getResultList();
        return dashboards;
    }
}
