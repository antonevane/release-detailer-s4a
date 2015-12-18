package com.dirtroadsoftware.rds4a.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown;
import com.dirtroadsoftware.rds4a.core.services.MaReleaseTownService;
import com.dirtroadsoftware.rds4a.core.services.util.MaReleaseTownList;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseTownListResource;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseTownResource;
import com.dirtroadsoftware.rds4a.rest.resources.asm.MaReleaseTownListResourceAsm;
import com.dirtroadsoftware.rds4a.rest.resources.asm.MaReleaseTownResourceAsm;

/**
 * Spring web controller for interacting with the MaReleaseTownService.
 */
@Controller
@RequestMapping("/rest/towns")
public class MaReleaseTownController {

    private final MaReleaseTownService townService;

    @Autowired
    public MaReleaseTownController(MaReleaseTownService townService) {
        this.townService = townService;
    }

    /**
     * Finds all {@link com.dirtroadsoftware.rds4a.core.models.entities.MaReleaseTown}.
     */
    @RequestMapping(value="/ma", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaReleaseTownListResource> getMaReleaseTowns() {
        MaReleaseTownList townsList = townService.findAllReleaseTowns();
        if (townsList != null) {
            MaReleaseTownListResource res = new MaReleaseTownListResourceAsm().toResource(townsList);
            return new ResponseEntity<MaReleaseTownListResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<MaReleaseTownListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/ma/{zipCode}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<MaReleaseTownResource> getMaReleaseTown(@PathVariable String zipCode) {
        MaReleaseTown town = townService.findReleaseTownByZipCode(zipCode);
        if (town != null) {
            MaReleaseTownResource res = new MaReleaseTownResourceAsm().toResource(town);
            return new ResponseEntity<MaReleaseTownResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<MaReleaseTownResource>(HttpStatus.NOT_FOUND);
        }
    }
}
