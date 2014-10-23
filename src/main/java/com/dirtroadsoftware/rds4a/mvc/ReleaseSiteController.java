package com.dirtroadsoftware.rds4a.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class ReleaseSiteController {
    @RequestMapping("/foo")
    public String test() {
        return "view";
    }

    @RequestMapping(value = "/bar")
    public String test2() {
        return "view";
    }
}
