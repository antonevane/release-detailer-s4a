package com.dirtroadsoftware.rds4a.core.services.impl;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.repositories.MaReleaseRepository;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.MaReleaseNotFoundException;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseList;
import com.dirtroadsoftware.rds4a.core.services.util.Rtn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class MaReleaseServiceImpl implements MaReleaseService {
    private final Logger logger = LoggerFactory.getLogger(MaReleaseServiceImpl.class);
    
    @Autowired
    MaReleaseRepository releaseRepository;

    @Transactional(readOnly = true)
    @Override
    public MaRelease findMaRelease(final Long id) {
        return releaseRepository.findOne(id);
    }

	@Transactional(readOnly = true)
	@Override
	public MaRelease findMaReleaseByRtn(final String rtn) {
		MaRelease release = null;
		try {
			Rtn parsedRtn = parseRtn(rtn);
			release = releaseRepository.findByRegionAndSite(parsedRtn.getRegion(), parsedRtn.getSite());
		} catch (MaReleaseNotFoundException releaseNotFound) {
			logger.warn("Exception while parsing rtn " + rtn, releaseNotFound);
		}
		return release;
	}

	@Transactional(readOnly = true)
	@Override
	public MaRelease findMaReleaseWithActionsByRtn(final String rtn) {
		MaRelease release = null;
		try {
			Rtn parsedRtn = parseRtn(rtn);
			release = releaseRepository.findMaReleaseWithActionsByRegionSite(parsedRtn.getRegion(),
					parsedRtn.getSite());
		} catch (MaReleaseNotFoundException releaseNotFound) {
			logger.warn("Exception while parsing rtn " + rtn, releaseNotFound);
		}
		return release;
	}

	@Transactional(readOnly = true)
    @Override
    public MaReleaseList findMaReleasesByTown(final String town) {
        List<MaRelease> releases = releaseRepository.findByTown(town);
        if (releases == null) {
            return new MaReleaseList(Collections.<MaRelease>emptyList());
        } else {
            return new MaReleaseList(releases);
        }
    }

	@Transactional(readOnly = true)
	@Override
	public MaReleaseList findMaReleasesByTown(final String town, final String sortBy, final String sortHow,
			final int offset, final int limit) {
		Pageable page = new PageRequest(offset, limit, Sort.Direction.fromString(sortHow), sortBy);
		List<MaRelease> releases = releaseRepository.findByTown(town, page);
		if (releases == null) {
			return new MaReleaseList(Collections.<MaRelease> emptyList());
		} else {
			return new MaReleaseList(releases);
		}
	}

	@Transactional(readOnly = true)
    @Override
    public Long countMaReleasesByTown(final String town) {
        return releaseRepository.countByTown(town);
    }

	@Transactional(readOnly = true)
    @Override
    public MaRelease findMaReleaseWithActionsById(final Long id) {
        return releaseRepository.findMaReleaseWithActions(id);
    }

    private Rtn parseRtn(final String rtn) {
        Rtn parsedRtn = null;
        try {
            parsedRtn = Rtn.parseRtn(rtn);
        } catch (ParseException ex) {
            throw new MaReleaseNotFoundException(ex);
        }
        return parsedRtn;
    }
}
