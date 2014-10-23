package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.com.dirtroadsoftware.rds4a.core.services.ReleaseSiteService;
import com.dirtroadsoftware.rds4a.core.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseSiteResourceAsm;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 *
 */
@Controller
@RequestMapping("/rest/release-sites")
public class ReleaseSiteController {
    private ReleaseSiteService service;

    public ReleaseSiteController(ReleaseSiteService service) {
        this.service = service;
    }
    @RequestMapping(value="/{releaseSiteId}", method = RequestMethod.GET)
    public ResponseEntity<ReleaseSiteResource> getReleaseSite(
            @PathVariable Long releaseSiteId) {
        ReleaseSite site = service.find(releaseSiteId);
        if (site != null) {
            ReleaseSiteResource res = new ReleaseSiteResourceAsm().toResource(site);
            return new ResponseEntity<ReleaseSiteResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ReleaseSiteResource>(HttpStatus.NOT_FOUND);
        }
    }
}
