package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.handler.DisputeHandler;
import ru.todo100.activer.handler.FlirtHandler;
import ru.todo100.activer.handler.PrivateMessageHandler;

import java.security.Principal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@SuppressWarnings("StatementWithEmptyBody")
@Controller
public class GlobalListenerController {
    @Autowired
    PrivateMessageHandler privateMessageHandler;
    @Autowired
    private DisputeHandler disputeHandler;
    @Autowired
    private FlirtHandler flirtHandler;

    @MessageMapping("/actions")
    public void message(final ReceiveMessageData message, final Principal principal) {
        if (message.getType().equals("DISPUTE_MESSAGE")) {
            disputeHandler.handle(message,principal);
            return;
        }
        if (message.getType().equals("FLIRT_MESSAGE")) {
            flirtHandler.handle(message, principal);
            return;
        }

        if (message.getType().equals("PRIVATE_MESSAGE")) {
            privateMessageHandler.handle(message, principal);
            return;
        }

        if (message.getType().equals("ADD_TO_FRIEND")) {
            //final AccountItem from = accountService.get(principal.getName());
            //friendDao.addRequest(from.getId(),message.getTo());
        }

    }
}
