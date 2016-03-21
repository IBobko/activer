package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/gifts")
public class GiftPageController {
    @RequestMapping("/")
    public String index() {
        return "gifts/index";
    }

}
