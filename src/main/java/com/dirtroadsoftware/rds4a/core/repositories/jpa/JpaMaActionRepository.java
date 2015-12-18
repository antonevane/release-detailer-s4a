package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

/**
 *
 */
@Deprecated
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class JpaMaActionRepository {
    @PersistenceContext
    private EntityManager em;

    public List<MaAction> findActionsByMaRelease(MaRelease release) {
        return null;
    }

    public MaAction findMaAction(long actionId) {
        return em.find(MaAction.class, actionId);
    }

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

	public Long countMaActions(String actionAttribute, String town) {
        Query query = em.createQuery("SELECT count(a) FROM MaAction a" +
                " WHERE a." + actionAttribute + " = ?1");
        query.setParameter(1, town);

        List results = query.getResultList();
        return (Long)results.iterator().next();
    }

}
