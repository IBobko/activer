package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/news")
public class NewsPageController {
    @RequestMapping("/")
    public String index() {
        return "news/index";
    }

}

