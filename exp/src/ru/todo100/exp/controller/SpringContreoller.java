package ru.todo100.exp.controller;

import com.sun.faces.action.RequestMapping;


import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Игорь on 08.11.2015.
 */
@RequestMapping("/")
@Controller
public class SpringContreoller {
    @RequestMapping("/")
    public String v() {
        return "index";
    }

}
