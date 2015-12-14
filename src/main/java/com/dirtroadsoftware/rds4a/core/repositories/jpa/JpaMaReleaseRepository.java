package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.repositories.MaReleaseRepository;

/**
 *
 */
public class JpaMaReleaseRepository {
    @PersistenceContext
    private EntityManager em;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MaRelease findMaRelease(Long id) {
        MaRelease maRelease = em.find(MaRelease.class, id);
        return maRelease;
    }

    public MaRelease findMaReleaseWithActions(Long id) {
        Query query = em.createQuery("SELECT r FROM MaRelease r " +
                "INNER JOIN FETCH r.actions WHERE r.id = :id");
        query.setParameter("id", id);
        List<MaRelease> releases = query.getResultList();
        if (releases == null) {
            return null;
        } else {
            return releases.get(0);
        }
    }

    public List<MaRelease> findMaReleasesByTown(String town) {
        Query query = em.createQuery("SELECT r from MaRelease r where r.town=?1");
        query.setParameter(1, town.toUpperCase());
        @SuppressWarnings("unchecked")
		List<MaRelease> releases = query.getResultList();
        return releases;
    }

    public MaRelease findMaReleaseByRegionSite(int region, int site) {
        Query query = em.createQuery("SELECT r FROM MaRelease r WHERE r.region = :region AND r.site = :site");
        query.setParameter("region", region);
        query.setParameter("site", site);
        @SuppressWarnings("unchecked")
		List<MaRelease> releases = query.getResultList();
        if (releases.isEmpty()) {
            return null;
        } else {
            return releases.get(0);
        }
    }

    public MaRelease findMaReleaseWithActionsByRegionSite(int region, int site) {
        Query query = em.createQuery("SELECT r FROM MaRelease r " +
                "INNER JOIN FETCH r.actions WHERE r.region= :region AND r.site= :site");
        query.setParameter("region", region);
        query.setParameter("site", site);
        @SuppressWarnings("unchecked")
		List<MaRelease> releases = query.getResultList();
        if (releases.isEmpty()) {
            return null;
        } else {
            MaRelease release = releases.get(0);
            return release;
        }
    }

    public List<String> findTownsWithReleasesByRegion(int region) {
        String sql = "SELECT DISTINCT(town) FROM ma_release_dev where region=?";
        List<String> towns = jdbcTemplate.queryForList(sql, new Object[] {region}, String.class);
        return towns;
    }

    public List<MaRelease> findMaReleasesByTown(String town, String sortBy, String sortHow, int offset, int limit) {
        Query query = em.createQuery("SELECT r FROM MaRelease r" +
                " WHERE r.town = :town" +
                " ORDER BY r." + sortBy
                + " " + (sortHow.equalsIgnoreCase("DESC") ? "DESC" : "ASC") + ", r.id DESC");
        query.setFirstResult(offset).setMaxResults(limit);
        query.setParameter("town", town.toUpperCase());

        @SuppressWarnings("unchecked")
		List<MaRelease> releases = query.getResultList();
        if (releases.isEmpty()) {
            return Collections.emptyList();
        } else {
            return releases;
        }
    }

    public Long countMaReleases(String releaseAttribute, String attributeValue) {
        Query query = em.createQuery("SELECT count(r) FROM MaRelease r" +
                " WHERE r." + releaseAttribute + " = ?1");
        query.setParameter(1, attributeValue);

        List results = query.getResultList();
        return (Long)results.iterator().next();
    }
}
