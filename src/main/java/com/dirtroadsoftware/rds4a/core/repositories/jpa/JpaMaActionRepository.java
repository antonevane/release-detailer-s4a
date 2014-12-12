package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.repositories.MaActionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 */
@Repository
public class JpaMaActionRepository implements MaActionRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MaAction> findActionsByMaRelease(MaRelease release) {
        return null;
    }

    @Override
    public MaAction findMaAction(long actionId) {
        return em.find(MaAction.class, actionId);
    }
}
