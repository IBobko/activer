package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.todo100.activer.data.MessageData;

import java.security.Principal;

/**
 * Created by igor on 20.03.16.
 */
@Controller
public class GlobalListenerController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/actions")
    public MessageData message(Message message, Principal principal)
    {
        template.convertAndSend("/global2/1","Пользователь хочет добавить вас в други");

        return null;
    }
}
