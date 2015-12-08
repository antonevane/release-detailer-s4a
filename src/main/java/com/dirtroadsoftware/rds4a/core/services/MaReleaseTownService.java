package com.dirtroadsoftware.rds4a.core.services;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseTownList;

/**
 *
 */
public interface MaReleaseTownService {
    MaReleaseTownList findAllReleaseTowns();

    MaReleaseTown findReleaseTownByZipCode(String zipCode);
}
