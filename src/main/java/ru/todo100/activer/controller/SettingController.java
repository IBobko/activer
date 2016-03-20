package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/settings")
public class SettingController {
    @RequestMapping("/")
    public String main() {

        return "settings/index";
    }
    @RequestMapping("/interests")
    public String interests() {
        return "settings/interests";
    }
    @RequestMapping("/trips")
    public String trips() {
        return "settings/trips";
    }
    @RequestMapping("/dreams")
    public String dreams() {
        return "settings/dreams";
    }
}
