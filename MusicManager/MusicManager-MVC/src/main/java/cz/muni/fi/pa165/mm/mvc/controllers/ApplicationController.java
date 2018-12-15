package cz.muni.fi.pa165.mm.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }
}
