package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;
import com.dirtroadsoftware.rds4a.core.repositories.MaReleaseTownRepository;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseTownService;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseTownList;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class MaReleaseTownServiceImpl implements MaReleaseTownService {

    @Autowired
    MaReleaseTownRepository townRepository;

    @Override
    public MaReleaseTownList findAllReleaseTowns() {
		List<MaReleaseTown> towns = Lists.newArrayList(townRepository.findAll());
        MaReleaseTownList townList = new MaReleaseTownList(towns);
        return townList;
    }

    @Override
    public MaReleaseTown findReleaseTownByZipCode(String zipCode) {
        return townRepository.findByZipCode(zipCode);
    }
}
