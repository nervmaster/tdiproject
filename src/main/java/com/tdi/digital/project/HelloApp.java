package com.tdi.digital.project;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApp {

    @RequestMapping("/")
    public String index() {
        return "CARALHA WORKS\n";
    }
}
