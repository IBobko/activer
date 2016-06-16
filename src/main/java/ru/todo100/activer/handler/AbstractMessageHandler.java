package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.PhotoAvatarSizeData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.AccountPhotoItem;
import ru.todo100.activer.populators.MessageAccountDataPopulator;
import ru.todo100.activer.service.PhotoService;

import java.security.Principal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public abstract class AbstractMessageHandler {
    private SimpMessagingTemplate template;
    protected SimpMessagingTemplate getTemplate() {
        return template;
    }

    private MessageAccountDataPopulator messageAccountDataPopulator;

    public MessageAccountDataPopulator getMessageAccountDataPopulator() {
        return messageAccountDataPopulator;
    }

    @Autowired
    public void setMessageAccountDataPopulator(MessageAccountDataPopulator messageAccountDataPopulator) {
        this.messageAccountDataPopulator = messageAccountDataPopulator;
    }

    @Autowired
    protected void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    public abstract void handle(final ReceiveMessageData message, final Principal principal);

    protected MessageAccountData generateAccountData(AccountItem account) {
        return getMessageAccountDataPopulator().populate(account);
    }
}
