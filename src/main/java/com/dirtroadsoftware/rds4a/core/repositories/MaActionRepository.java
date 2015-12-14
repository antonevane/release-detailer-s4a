package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface MaActionRepository extends PagingAndSortingRepository<MaAction, Long>{
    public List<MaAction> findActionsByMaRelease(MaRelease release);
    public List<MaAction> findByReleaseTown (String town, String sortBy, String sortHow, int offset, int limit);
    public List<MaAction> findMaActionsByDate(String date, String sortBy, String sortHow, int offset, int limit);
    public Long countMaActions(String actionAttribute, String attributeValue);
}
