package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/safety")
public class SafetyPageController {
    @RequestMapping("")
    public String index() {
        return "safety/index";
    }

}
