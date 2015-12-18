package com.dirtroadsoftware.rds4a.core.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;

/**
 *
 */
public interface MaReleaseRepository extends PagingAndSortingRepository<MaRelease, Long> {
    /**
     * Fetch the release by id, but also make sure the associated actions are
     * loaded and available.
     *
     * @param id
     * @return
     */
	@Query("SELECT r FROM MaRelease r INNER JOIN FETCH r.actions WHERE r.id = :id")
	public MaRelease findMaReleaseWithActions(Long id);

	public List<MaRelease> findByTown(String town);

	public MaRelease findByRegionAndSite(int region, int site);

	@Query("SELECT r FROM MaRelease r INNER JOIN FETCH r.actions WHERE r.region= :region AND r.site= :site")
	public MaRelease findMaReleaseWithActionsByRegionSite(int region, int site);

	public List<String> findDistinctTownsByRegion(int region);

	public List<MaRelease> findByTown(String town, Pageable pageable);

	public Long countByTown(String town);
}
