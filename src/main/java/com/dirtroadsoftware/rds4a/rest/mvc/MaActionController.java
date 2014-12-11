package com.dirtroadsoftware.rds4a.rest.mvc;

import com.dirtroadsoftware.rds4a.core.services.MaActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Spring web controller for interacting with the MaActionService.
 */
@Controller
@RequestMapping("/rest/actions")
public class MaActionController {
    /** Service exposed by this web controller */
    private MaActionService service;
    /** Create controller for a service */

    @Autowired
    public MaActionController(MaActionService service) {
        this.service = service;
    }

//    /** Finds a {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease} by id */
//    @RequestMapping(value="/{actionId}", method = RequestMethod.GET)
//    @PreAuthorize("permitAll")
//    public ResponseEntity<MaActionResource> getActionsByReleaseId(
//            @PathVariable Long releaseId) {
//        MaAction action = service.findMaAction(actionId);
//        if (action != null) {
//            MaActionResource res = new MaActionResourceAsm().toResource(action);
//            return new ResponseEntity<MaActionResource>(res, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<MaActionResource>(HttpStatus.NOT_FOUND);
//        }
//    }
}
