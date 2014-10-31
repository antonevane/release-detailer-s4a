package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.services.ReleaseSiteService;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseSiteResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Spring web controller for interacting with the {@link ReleaseSiteService}.
 */
@Controller
@RequestMapping("/rest/release-sites")
public class ReleaseSiteController {
    /** Service exposed by this web controller */
    private ReleaseSiteService service;

    /** Create controller for a service */
    public ReleaseSiteController(ReleaseSiteService service) {
        this.service = service;
    }

    /** Finds a {@link ReleaseSite} by id */
    @RequestMapping(value="/{releaseSiteId}", method = RequestMethod.GET)
    public ResponseEntity<ReleaseSiteResource> getReleaseSite(
            @PathVariable Long releaseSiteId) {
        ReleaseSite site = service.findReleaseSite(releaseSiteId);
        if (site != null) {
            ReleaseSiteResource res = new ReleaseSiteResourceAsm().toResource(site);
            return new ResponseEntity<ReleaseSiteResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ReleaseSiteResource>(HttpStatus.NOT_FOUND);
        }
    }

    /** Deletes a {@link ReleaseSite} by id */
    @RequestMapping(value="/{releaseSiteId}", method=RequestMethod.DELETE)
    public ResponseEntity<ReleaseSiteResource> deleteReleaseSite(@PathVariable Long releaseSiteId) {
        ReleaseSite site = service.deleteReleaseSite(releaseSiteId);
        if (site != null) {
            ReleaseSiteResource res = new ReleaseSiteResourceAsm().toResource(site);
            return new ResponseEntity<ReleaseSiteResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ReleaseSiteResource>(HttpStatus.NOT_FOUND);
        }
    }

    /** Updates a {@link ReleaseSite} with the supplied details */
    @RequestMapping(value="/{releaseSiteId}", method=RequestMethod.PUT)
    public ResponseEntity<ReleaseSiteResource> updateReleaseSite(@PathVariable Long releaseSiteId,
                                                                 @RequestBody ReleaseSiteResource sentReleaseSite) {
        ReleaseSite updatedSite = service.updateReleaseSite(releaseSiteId, sentReleaseSite.toReleaseSite());
        if (updatedSite != null) {
            ReleaseSiteResource res = new ReleaseSiteResourceAsm().toResource(updatedSite);
            return new ResponseEntity<ReleaseSiteResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ReleaseSiteResource>(HttpStatus.NOT_FOUND);
        }
    }
}
