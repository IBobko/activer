package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/payeer")
public class PayeerController {
    @ResponseBody
    @RequestMapping("/success")
    public String successPage() {
        return "1";
    }


    @ResponseBody
    @RequestMapping("/fail")
    public String failedPage() {
        return "1";
    }

    @ResponseBody
    @RequestMapping("/handler")
    public String handlerPage() {
        return "1";
    }
}
