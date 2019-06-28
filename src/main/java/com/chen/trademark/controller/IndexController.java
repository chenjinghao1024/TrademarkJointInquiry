package com.chen.trademark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"/","/index"})
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping("/record")
    public String record(ModelMap map) {
        return "record";
    }

    @RequestMapping("/query")
    public String query(ModelMap map) {
        return "query";
    }
}