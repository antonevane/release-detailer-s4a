package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.repositories.MaReleaseRepository;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.MaReleaseNotFoundException;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseList;
import com.dirtroadsoftware.rds4a.core.services.util.Rtn;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class MaReleaseServiceImpl implements MaReleaseService {
    static final Logger logger = Logger.getLogger(MaReleaseServiceImpl.class);
    @Autowired
    MaReleaseRepository releaseRepository;

    @Override
    public MaRelease findMaRelease(Long id) {
        return releaseRepository.findOne(id);
    }

    @Override
    public MaRelease findMaReleaseByRtn(String rtn) {
        Rtn parsedRtn = parseRtn(rtn);
        return releaseRepository.findByRegionAndSite(parsedRtn.getRegion(), parsedRtn.getSite());
    }

    @Override
    public MaRelease findMaReleaseWithActionsByRtn(String rtn) {
        Rtn parsedRtn = parseRtn(rtn);
        return releaseRepository.findMaReleaseWithActionsByRegionSite(parsedRtn.getRegion(), parsedRtn.getSite());
    }

    @Override
    public MaReleaseList findMaReleasesByTown(String town) {
        List<MaRelease> releases = releaseRepository.findByTown(town);
        if (releases == null) {
            return new MaReleaseList(Collections.<MaRelease>emptyList());
        } else {
            return new MaReleaseList(releases);
        }
    }

    @Override
    public MaReleaseList findMaReleasesByTown(String town, String sortBy, String sortHow, int offset, int limit) {
    	Pageable page = new PageRequest(offset, limit, Sort.Direction.fromString(sortHow), sortBy); 
        List<MaRelease> releases = releaseRepository.findByTown(town, page);
        if (releases == null) {
            return new MaReleaseList(Collections.<MaRelease>emptyList());
        } else {
            return new MaReleaseList(releases);
        }
    }

    @Override
    public Long countMaReleasesByTown(String town) {
        return releaseRepository.countByTown(town);
    }

    @Override
    public MaRelease findMaReleaseWithActionsById(Long id) {
        return releaseRepository.findMaReleaseWithActions(id);
    }

    private Rtn parseRtn(String rtn) {
        Rtn parsedRtn = null;
        try {
            parsedRtn = Rtn.parseRtn(rtn);
        } catch (ParseException ex) {
            logger.warn("Exception while parsing rtn " + rtn, ex);
            throw new MaReleaseNotFoundException(ex);
        }
        return parsedRtn;
    }
}
