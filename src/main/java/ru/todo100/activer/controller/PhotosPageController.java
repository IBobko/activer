package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/{id}")
    public String another() {
        return "photos/another";
    }

    @RequestMapping("/{id}/photo-{photo}")
    public String photo() {
        return "photos/photo";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add() {
        return "photos/add";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload() {
        return "redirect:/photos";
    }
}
