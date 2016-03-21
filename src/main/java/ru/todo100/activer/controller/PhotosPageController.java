package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/photos")
public class PhotosPageController {
    @RequestMapping("/")
    public String index() {
        return "photos/index";
    }
}
