package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.repositories.AccountRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
    @SuppressWarnings("unchecked")
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("SELECT a FROM Account a");
        List<Account> accounts = query.getResultList();
        if (accounts.size() == 0) {
            return null;
        } else {
            return accounts;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Account findAccountByName(String name) {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.name=?1");
        query.setParameter(1, name);
        List<Account> accounts = query.getResultList();
        if (accounts.isEmpty()) {
            return null;
        } else {
            return accounts.get(0);
        }
    }
}
