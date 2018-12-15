package cz.muni.fi.pa165.mm.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class represents the default application mapping
 * @author Yehor Safonov; 487596
 */

@Controller
public class ApplicationController {
    /**
     * Roting the application to the home.jsp
     */
    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }
}
