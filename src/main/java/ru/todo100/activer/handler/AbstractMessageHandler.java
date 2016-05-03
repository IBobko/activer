package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.todo100.activer.data.ReceiveMessageData;

import java.security.Principal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public abstract class AbstractMessageHandler {
    private SimpMessagingTemplate template;

    protected SimpMessagingTemplate getTemplate() {
        return template;
    }

    @Autowired
    protected void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    public abstract void handle(final ReceiveMessageData message, final Principal principal);
}
