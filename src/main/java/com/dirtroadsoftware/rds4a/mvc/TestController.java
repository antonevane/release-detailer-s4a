package com.dirtroadsoftware.rds4a.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class TestController {
    @RequestMapping(name = "/test")
    public String test() {
        return "view";
    }
}
