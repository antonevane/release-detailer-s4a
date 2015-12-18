package com.dirtroadsoftware.rds4a.core.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;

/**
 *
 */
public interface MaReleaseTownRepository extends PagingAndSortingRepository<MaReleaseTown, Long> {
	List<MaReleaseTown> findByRegion(int region);

	MaReleaseTown findByZipCode(String zipCode);
}
