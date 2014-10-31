package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;

/**
 * Find, update and delete {@link ReleaseSite}
 */
public interface ReleaseSiteService {
    /**
     * Finds the {@link ReleaseSite} with the given id
     *
     * @param id the id of the {@link ReleaseSite} to lookup
     * @return the {@link ReleaseSite} if it exists, otherwise null
     */
    public ReleaseSite find(Long id);

    /**
     * Deletes the {@link ReleaseSite} with the given id
     *
     * @param id the id of the {@link ReleaseSite} to delete
     * @return the deleted {@link ReleaseSite} if it exists, otherwise null
     */
    public ReleaseSite delete(Long id);

    /**
     * Updates {@link ReleaseSite} with all new information
     *
     * @param id the id of the {@link ReleaseSite} being updated
     * @param releaseSite the releaseSite information used to update the specified {@link ReleaseSite}
     * @return the updated {@link ReleaseSite} or null if it does not exist
     */
    public ReleaseSite update(Long id, ReleaseSite releaseSite);
}
