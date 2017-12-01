package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@SuppressWarnings("FieldCanBeLocal")
@Controller
@RequestMapping
public class HomePageController {
    final private String AUTH_REDIRECT = "redirect:/auth";
    final private String PROFILE_REDIRECT = "redirect:/profile";

    @RequestMapping
    public String index(final Principal principal) {
        if (null == principal) {
            return AUTH_REDIRECT;
        }
        return PROFILE_REDIRECT;
    }

    @ResponseBody
    @RequestMapping("/payeer_433788345.txt")
    public String payeer() {
        return "433788345";
    }
}
