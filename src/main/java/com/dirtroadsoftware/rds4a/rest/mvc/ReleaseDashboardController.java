package com.dirtroadsoftware.rds4a.rest.mvc;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseDashboard;
import com.dirtroadsoftware.rds4a.core.models.entities.ReleaseSite;
import com.dirtroadsoftware.rds4a.core.services.ReleaseDashboardService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardNotFoundException;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseDashboardList;
import com.dirtroadsoftware.rds4a.core.services.util.ReleaseSiteList;
import com.dirtroadsoftware.rds4a.rest.exceptions.NotFoundException;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardListResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseDashboardResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteListResource;
import com.dirtroadsoftware.rds4a.rest.resources.ReleaseSiteResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseDashboardListResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseDashboardResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseSiteListResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.ReleaseSiteResourceAsm;

/**
 * TODO use singular "release-dashboard" for path
 */
@RestController
@RequestMapping(value="/rest/release-dashboards")
public class ReleaseDashboardController {
    private ReleaseDashboardService releaseDashboardService;

    @Autowired
    public ReleaseDashboardController(ReleaseDashboardService service) {
        this.releaseDashboardService = service;
    }

    @RequestMapping(value="/{releaseDashboardId}", method = RequestMethod.GET)
    public ResponseEntity<ReleaseDashboardResource> getReleaseDashboard(@PathVariable Long releaseDashboardId) {
        ReleaseDashboard dashboard = releaseDashboardService.findReleaseDashboard(releaseDashboardId);
        if (dashboard != null) {
            ReleaseDashboardResource res = new ReleaseDashboardResourceAsm().toResource(dashboard);
            return new ResponseEntity<ReleaseDashboardResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ReleaseDashboardResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{releaseDashboardId}/release-sites", method = RequestMethod.GET)
    public ResponseEntity<ReleaseSiteListResource> findAllReleaseSites(@PathVariable Long releaseDashboardId) {
        try {
            ReleaseSiteList siteList = releaseDashboardService.findAllReleaseSites(releaseDashboardId);
            ReleaseSiteListResource res = new ReleaseSiteListResourceAsm().toResource(siteList);
            return new ResponseEntity<ReleaseSiteListResource>(res, HttpStatus.OK);
        } catch (ReleaseDashboardNotFoundException ex) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<ReleaseDashboardListResource> findAllReleaseDashboards() {
        ReleaseDashboardList dashboardList = releaseDashboardService.findAllReleaseDashboards();

        if (dashboardList != null) {
            ReleaseDashboardListResource res = new ReleaseDashboardListResourceAsm().toResource(dashboardList);
            return new ResponseEntity<ReleaseDashboardListResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ReleaseDashboardListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{releaseDashboardId}/release-sites", method=RequestMethod.POST)
    public ResponseEntity<ReleaseSiteResource> createReleaseSite(
            @PathVariable Long releaseDashboardId,
            @RequestBody ReleaseSiteResource sentReleaseSite) {

        try {
            ReleaseSite createdReleaseSite = releaseDashboardService.createReleaseSite(releaseDashboardId, sentReleaseSite.toReleaseSite());
            ReleaseSiteResource createdResource = new ReleaseSiteResourceAsm().toResource(createdReleaseSite);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink(Link.REL_SELF).getHref()));

            return new ResponseEntity<ReleaseSiteResource>(createdResource, headers, HttpStatus.CREATED);
        } catch (ReleaseDashboardNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
}
