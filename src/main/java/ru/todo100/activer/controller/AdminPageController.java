package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
public class AdminPageController {
    @Autowired
    private SimpMessagingTemplate template;
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request) {


        String subscribe = request.getParameter("subscribe");

        template.convertAndSend(subscribe,"hello");

        return "admin/index";
    }
}
