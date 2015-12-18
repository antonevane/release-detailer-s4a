package com.dirtroadsoftware.rds4a.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.services.MaActionService;
import com.dirtroadsoftware.rds4a.rest.exceptions.NotFoundException;
import com.dirtroadsoftware.rds4a.rest.resources.MaActionResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.MaActionResourceAsm;

/**
 * Spring web controller for interacting with the MaActionService.
 */
@RestController
@RequestMapping("/rest/actions")
public class MaActionController {
    /** Service exposed by this web controller */
    private MaActionService service;
    /** Create controller for a service */

    @Autowired
    public MaActionController(MaActionService service) {
        this.service = service;
    }

    /** Finds a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} by id */
    @RequestMapping(value="/{actionId}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaActionResource> getActionById(@PathVariable Long actionId) {
        MaAction action = service.findActionById(actionId);
        if (action != null) {
            MaActionResource res = new MaActionResourceAsm().toResource(action);
            return new ResponseEntity<MaActionResource>(res, HttpStatus.OK);
        } else {
            throw new NotFoundException("MaAction action not found. [ACTION_ID] = " + actionId);
        }
    }
}
