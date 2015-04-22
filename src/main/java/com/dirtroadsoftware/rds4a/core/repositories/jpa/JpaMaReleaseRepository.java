package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.repositories.MaReleaseRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
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

    @Override
    public List<MaRelease> findMaReleasesByTown(String town) {
        Query query = em.createQuery("SELECT r from MaRelease r where r.town=?1");
        query.setParameter(1, town.toUpperCase());
        List<MaRelease> releases = query.getResultList();
        return releases;
    }

    @Override
    public MaRelease findMaReleaseByRegionSite(int region, int site) {
        Query query = em.createQuery("SELECT r FROM MaRelease r WHERE r.region = :region AND r.site = :site");
        query.setParameter("region", region);
        query.setParameter("site", site);
        List<MaRelease> releases = query.getResultList();
        if (releases.isEmpty()) {
            return null;
        } else {
            return releases.get(0);
        }
    }

    @Override
    public MaRelease findMaReleaseWithActionsByRegionSite(int region, int site) {
        Query query = em.createQuery("SELECT r FROM MaRelease r " +
                "INNER JOIN FETCH r.actions WHERE r.region= :region AND r.site= :site");
        query.setParameter("region", region);
        query.setParameter("site", site);
        List<MaRelease> releases = query.getResultList();
        if (releases.isEmpty()) {
            return null;
        } else {
            MaRelease release = releases.get(0);
            return release;
        }
    }

    @Override
    public List<String> findTownsWithReleasesByRegion(int region) {
        String sql = "SELECT DISTINCT(town) FROM ma_release_dev where region=?";
        List<String> towns = jdbcTemplate.queryForList(sql, new Object[] {region}, String.class);
        return towns;
    }

    @Override
    public List<MaAction> findMaActionsByTown(String town, String sortBy, String sortHow, int offset, int limit) {
        Query query = em.createQuery("SELECT a FROM MaAction a" +
                " WHERE a.release.town = :town" +
                " ORDER BY a." + sortBy
                + " " + (sortHow.equalsIgnoreCase("DESC") ? "DESC" : "ASC") + ", a.id DESC");
        query.setFirstResult(offset).setMaxResults(limit);
        query.setParameter("town", town.toUpperCase());

        List<MaAction> actions = query.getResultList();
        if (actions.isEmpty()) {
            return Collections.emptyList();
        } else {
            return actions;
        }
    }

    @Override
    public List<MaAction> findMaActionsByDate(String date, String sortBy, String sortHow, int offset, int limit) {
        Query query;
        if ("raoClass".equals(sortBy)) {
            query = em.createQuery("SELECT a FROM MaAction a" +
                    " WHERE a.date = :date" +
                    " ORDER BY CASE WHEN a.release.raoClass = '' THEN 2 ELSE 1 END, a.release.raoClass "
                    + " " + (sortHow.equalsIgnoreCase("DESC") ? "DESC" : "ASC") + ", a.id DESC");
        } else {
            query = em.createQuery("SELECT a FROM MaAction a" +
                    " WHERE a.date = :date" +
                    " ORDER BY a.release." + sortBy
                    + " " + (sortHow.equalsIgnoreCase("DESC") ? "DESC" : "ASC") + ", a.id DESC");
        }
        query.setFirstResult(offset).setMaxResults(limit);
        query.setParameter("date", java.sql.Date.valueOf(date));

        List<MaAction> actions = query.getResultList();
        if (actions.isEmpty()) {
            return Collections.emptyList();
        } else {
            return actions;
        }
    }

}
