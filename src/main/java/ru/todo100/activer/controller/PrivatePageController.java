package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/private")
public class PrivatePageController {
    @RequestMapping("")
    public String index() {
        return "private/index";
    }
}
