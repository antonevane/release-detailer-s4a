package com.dirtroadsoftware.rds4a.core.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

/**
 *
 */
public interface MaActionRepository extends PagingAndSortingRepository<MaAction, Long> {
	public List<MaAction> findByRelease(MaRelease release);

	public List<MaAction> findByReleaseTown(String town, Pageable pageable);

	// TODO: Implement second find with a proper sort
	public List<MaAction> findByDate(String date, Pageable pageable);

	public Long countByReleaseTown(String town);
}
