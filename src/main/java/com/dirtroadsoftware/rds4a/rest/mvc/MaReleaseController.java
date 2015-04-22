package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.services.MaActionService;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.TownNotFoundException;
import com.dirtroadsoftware.rds4a.core.services.util.MaActionList;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseList;
import com.dirtroadsoftware.rds4a.rest.exceptions.BadRequestException;
import com.dirtroadsoftware.rds4a.rest.exceptions.NotFoundException;
import com.dirtroadsoftware.rds4a.rest.resources.MaActionListResource;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseListResource;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.MaActionListResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.MaReleaseListResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.MaReleaseResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Spring web controller for interacting with the MaReleaseService.
 */
@Controller
@RequestMapping("/rest/releases")
public class MaReleaseController {
    /** Service exposed by this web controller */
    private MaReleaseService releaseService;

    /** Create controller for a service */
    @Autowired
    public MaReleaseController(MaReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    /** Finds a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} by id */
    @RequestMapping(value="/{releaseId}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaReleaseResource> getMaRelease(@PathVariable Long releaseId) {
        MaRelease release = releaseService.findMaRelease(releaseId);
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
        MaRelease release = releaseService.findMaReleaseByRtn(rtn);
        if (release != null) {
            MaReleaseResource res = new MaReleaseResourceAsm().toResource(release);
            return new ResponseEntity<MaReleaseResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<MaReleaseResource>(HttpStatus.NOT_FOUND);
        }
    }

    /** Finds a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} by Town (Release Tracking Number) */
    @RequestMapping(value="/ma/town/{town}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaReleaseListResource> getMaReleasesByTown(@PathVariable String town) {

        try {
            MaReleaseList releaseList = releaseService.findMaReleasesByTown(town);
            MaReleaseListResource res = new MaReleaseListResourceAsm().toResource(releaseList);
            return new ResponseEntity<MaReleaseListResource>(res, HttpStatus.OK);
        } catch (TownNotFoundException ex) {
            throw new NotFoundException();
        }
     }

    @RequestMapping(value="/ma/{rtn}/actions", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaActionListResource> findAllActions(@PathVariable String rtn) {
        MaRelease release = releaseService.findMaReleaseWithActionsByRtn(rtn);
        if (release == null) {
            throw new NotFoundException();
        }
        MaActionList actions = new MaActionList(release.getActions());
        MaActionListResource res = new MaActionListResourceAsm().toResource(actions);
        return new ResponseEntity<MaActionListResource>(res, HttpStatus.OK);
    }

//    @RequestMapping(value="", method = RequestMethod.GET, params = {"offset", "limit"})
//    @ResponseBody
//    @PreAuthorize("permitAll")
//    public ResponseEntity<MaReleaseListResource> findReleasesOrderByLatestActionDate(@RequestParam("offset") String offset, @RequestParam("limit") String limit) {
//        MaReleaseList releaseList = releaseService.findMaReleasesOrderByLatestActionDate(Integer.parseInt(offset), Integer.parseInt(limit));
//        MaReleaseListResource res = new MaReleaseListResourceAsm().toResource(releaseList);
//        return new ResponseEntity<MaReleaseListResource>(res, HttpStatus.OK);
//    }

    public List<MaRelease> findMaReleasesByTown(Long townId) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

}
