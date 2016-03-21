package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/settings")
public class SettingController {

    @RequestMapping("/")
    public String main(Model model) {
        model.addAttribute("pageType","settings");
        return "settings/index";
    }

    @RequestMapping("/interests")
    public String interests(Model model) {
        model.addAttribute("pageType","settings");
        return "settings/interests";
    }

    @RequestMapping("/trips")
    public String trips(Model model) {
        model.addAttribute("pageType","settings");
        return "settings/trips";
    }

    @RequestMapping("/dreams")
    public String dreams(Model model) {
        model.addAttribute("pageType","settings");
        return "settings/dreams";
    }
}
