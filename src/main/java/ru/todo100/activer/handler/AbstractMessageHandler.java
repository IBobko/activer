package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.PhotoService;

import java.security.Principal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public abstract class AbstractMessageHandler {
    private SimpMessagingTemplate template;
    @Autowired
    private PhotoService photoService1;

    protected SimpMessagingTemplate getTemplate() {
        return template;
    }

    @Autowired
    protected void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    public abstract void handle(final ReceiveMessageData message, final Principal principal);

    protected MessageAccountData generateAccountData(AccountItem account) {
        final MessageAccountData messageAccountData = new MessageAccountData();
        messageAccountData.setFirstName(account.getFirstName());
        messageAccountData.setLastName(account.getLastName());
        messageAccountData.setId(account.getId());
        messageAccountData.setPhoto60x60(photoService1.getPhoto(account.getId()));
        return messageAccountData;
    }
}
