package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseService;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.MaReleaseResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Spring web controller for interacting with the {@link com.dirtroadsoftware.rds4a.core.services.ReleaseSiteService}.
 */
@Controller
@RequestMapping("/rest/releases")
public class MaReleaseController {
    /** Service exposed by this web controller */
    private MaReleaseService service;

    /** Create controller for a service */
    @Autowired
    public MaReleaseController(MaReleaseService service) {
        this.service = service;
    }

    /** Finds a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} by id */
    @RequestMapping(value="/{releaseId}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaReleaseResource> getMaRelease(
            @PathVariable Long releaseId) {
        MaRelease release = service.findMaRelease(releaseId);
        if (release != null) {
            MaReleaseResource res = new MaReleaseResourceAsm().toResource(release);
            return new ResponseEntity<MaReleaseResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<MaReleaseResource>(HttpStatus.NOT_FOUND);
        }
    }

    /** Finds a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} by MassDEP RTN (Release Tracking Number) */
    @RequestMapping(value="/ma/{rtn}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaReleaseResource> getMaReleaseByRtn(
            @PathVariable String rtn) {
        MaRelease release = service.findMaReleaseByRtn(rtn);
        if (release != null) {
            MaReleaseResource res = new MaReleaseResourceAsm().toResource(release);
            return new ResponseEntity<MaReleaseResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<MaReleaseResource>(HttpStatus.NOT_FOUND);
        }
    }

    public List<MaRelease> findMaReleasesByTown(Long townId) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
