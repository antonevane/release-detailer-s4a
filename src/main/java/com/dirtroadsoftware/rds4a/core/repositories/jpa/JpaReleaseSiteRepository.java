package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.repositories.ReleaseSiteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 */
@Repository
public class JpaReleaseSiteRepository implements ReleaseSiteRepository {
    @PersistenceContext
    EntityManager em;

    public ReleaseSite createReleaseSite(ReleaseSite data) {
        em.persist(data);
        return data;
    }

    public ReleaseSite findReleaseSite(Long id) {
        return em.find(ReleaseSite.class, id);
    }

    public ReleaseSite deleteReleaseSite(Long id) {
        ReleaseSite releaseSite = findReleaseSite(id);
        em.remove(releaseSite);
        return releaseSite;
    }

    public ReleaseSite updateReleaseSite(Long id, ReleaseSite releaseSite) {
        ReleaseSite rs = findReleaseSite(id);
        rs.setRegion(releaseSite.getRegion());
        rs.setSite(releaseSite.getSite());
        return rs;
    }

    @SuppressWarnings("unchecked")
    public List<ReleaseSite> findByReleaseDashboardId(Long releaseDashboardId) {
        Query query = em.createQuery("SELECT rs FROM ReleaseSite rs, ReleaseDashboard rd WHERE rs.dashboard=rd AND rd.id=?1");
        query.setParameter(1, releaseDashboardId);
        List<ReleaseSite> releaseSites = query.getResultList();
        return releaseSites;
    }
}
