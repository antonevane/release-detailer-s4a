package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.repositories.MaReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.List;

/**
 *
 */
@Repository
public class JpaMaReleaseRepository implements MaReleaseRepository {
    @PersistenceContext
    private EntityManager em;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public MaRelease findMaRelease(Long id) {
        MaRelease maRelease = em.find(MaRelease.class, id);
        return maRelease;
    }

    @Override
    public List<MaRelease> findMaReleasesByTown(String town) {
        Query query = em.createQuery("SELECT r from MaRelease r where r.town=?1");
        query.setParameter(1, town);
        List<MaRelease> releases = query.getResultList();
        return releases;
    }

    @Override
    public MaRelease findMaReleaseByRegionSite(int region, int site) {
        Query query = em.createQuery("SELECT r FROM MaRelease r WHERE r.region=?1 AND r.site=?2");
        query.setParameter(1, region);
        query.setParameter(2, site);
        List<MaRelease> releases = query.getResultList();
        if (releases.isEmpty()) {
            return null;
        } else {
            return releases.get(0);
        }
    }

    @Override
    public List<String> findTownsWithReleasesByRegion(int region) {
        String sql = "SELECT DISTINCT(town) FROM ma_release_dev where region=?";
        List<String> towns = jdbcTemplate.queryForList(sql, new Object[] {region}, String.class);
        return towns;
    }
}
