package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.repositories.AccountRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository bean that implements the Account DAO {@link AccountRepository}. Uses the container-managed
 * EntityManager to access the persistence context.
 */
@Repository
public class JpaAccountRepository implements AccountRepository {

    /**
     * Container-managed entity manager configured with transactional scope
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public Account findAccount(Long id) {
        return em.find(Account.class, id);
    }


    @Override
    public Account createAccount(Account account) {
        em.persist(account);
        return account;
    }

    @Override
    public ReleaseDashboard createReleaseDashboard(Long accountId, ReleaseDashboard dashboard) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
