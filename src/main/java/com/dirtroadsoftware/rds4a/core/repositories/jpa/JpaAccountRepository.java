package com.dirtroadsoftware.rds4a.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dirtroadsoftware.rds4a.core.models.entities.Account;
import com.dirtroadsoftware.rds4a.core.repositories.AccountRepository;

/**
 * Repository bean that implements the Account DAO {@link AccountRepository}. Uses the container-managed
 * EntityManager to access the persistence context.
 */
@Deprecated
public class JpaAccountRepository {
    /**
     * Container-managed entity manager configured with transactional scope
     */
    @PersistenceContext
    private EntityManager em;

    public Account findAccount(Long id) {
        return em.find(Account.class, id);
    }


    public Account createAccount(Account account) {
        em.persist(account);
        return account;
    }

    @SuppressWarnings("unchecked")
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("SELECT a FROM Account a");
        List<Account> accounts = query.getResultList();
        return accounts;
    }

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
