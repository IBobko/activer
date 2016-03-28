package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;

import java.security.Principal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
public class GlobalListenerController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/actions")
    public void message(final Message<ReceiveMessageData> message, final Principal principal) {
        switch (message.getPayload().getType()) {
            case "private-message": /*todo something*/
                break;
            case "add-to-friend": /*todo something*/
                break;
            case "like-message": /*todo something*/
                break;
            case "wall-message": /*todo something*/
                break;
        }

        PacketMessageData messageData = new PacketMessageData();
        template.convertAndSend("/global2/1", messageData);

        template.convertAndSend("/global2/1", messageData);


        //template.convertAndSendToUser(principal.getName(),"");


    }
}
