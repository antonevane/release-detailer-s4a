package com.dirtroadsoftware.rds4a.core.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;

/**
 *
 */
public interface ReleaseDashboardRepository extends PagingAndSortingRepository<ReleaseDashboard, Long> {
	public ReleaseDashboard findByTitle(String title);

	public List<ReleaseDashboard> findByOwnerId(Long accountId);
}
