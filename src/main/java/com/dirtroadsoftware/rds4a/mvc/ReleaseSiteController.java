package com.dirtroadsoftware.rds4a.mvc;

import com.dirtroadsoftware.rds4a.entities.ReleaseSite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class ReleaseSiteController {
    @RequestMapping(value="/foo", method = RequestMethod.POST)
    public @ResponseBody ReleaseSite test(@RequestBody ReleaseSite release) {
        return release;
    }

    @RequestMapping(value = "/bar")
    public String test2() {
        return "view";
    }
}
