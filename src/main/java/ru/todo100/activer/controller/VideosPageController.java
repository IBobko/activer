package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/videos")
public class VideosPageController {
    @RequestMapping("/")
    public String index() {
        return "videos/index";
    }
}
