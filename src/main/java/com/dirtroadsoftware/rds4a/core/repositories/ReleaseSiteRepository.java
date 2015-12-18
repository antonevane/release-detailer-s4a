package com.dirtroadsoftware.rds4a.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;

/**
 *
 */
public interface ReleaseSiteRepository extends PagingAndSortingRepository<ReleaseSite, Long> {
	@Modifying
	@Query("update ReleaseSite rs set rs.region = ?2, rs.site = ?3 where rs.id = ?1")
	public ReleaseSite updateSiteAndRegion(Long releaseSiteId, int region, int site);

	//@Query("SELECT rs FROM ReleaseSite rs, ReleaseDashboard rd WHERE rs.dashboard=rd AND rd.id=?1")
	public List<ReleaseSite> findByDashboardId(Long releaseDashboardId);
}
