package ru.todo100.activer.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import ru.todo100.activer.data.MessageData;

import java.security.Principal;

/**
 * Created by igor on 20.03.16.
 */
public class GlobalListenerController {

    @MessageMapping("/actions")
    public MessageData message(Message message, Principal principal)
    {


        return null;
    }
}
