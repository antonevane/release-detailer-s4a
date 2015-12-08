package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;
import com.dirtroadsoftware.rds4a.core.repositories.MaReleaseTownRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 */
@Repository
public class JpaMaReleaseTownRepository implements MaReleaseTownRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MaReleaseTown> findTownsWithReleasesByRegion(int region) {
        Query query = em.createQuery("SELECT t FROM MaReleaseTown t where t.region=?1");
        query.setParameter(1, region);
        List<MaReleaseTown> townsByRegion = query.getResultList();
        return townsByRegion;
    }

    @Override
    public List<MaReleaseTown> findAllTownsWithReleases() {
        Query query = em.createQuery("SELECT t FROM MaReleaseTown t");
        List<MaReleaseTown> towns = query.getResultList();
        return towns;
    }

    @Override
    public MaReleaseTown findTownByZipCode(String zipCode) {
        Query query = em.createQuery("SELECT t from MaReleaseTown t where t.zipCode=?1");
        query.setParameter(1, zipCode);
        List<MaReleaseTown> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }
}
