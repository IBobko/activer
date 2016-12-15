package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/agreement")
public class AgreementPageController {
    @RequestMapping("/main")
    public String main() {

        return "agreement/main";
    }

    @RequestMapping("/supplementary")
    public String supplementary() {
        return "agreement/supplementary";
    }
}
