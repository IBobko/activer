package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.form.ChangePasswordForm;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/premium")
public class PremiumPageController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "premium/index";
    }
}
