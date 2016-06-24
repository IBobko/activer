package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.populators.Facade;
import ru.todo100.activer.populators.MessageAccountDataPopulator;

import java.security.Principal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public abstract class AbstractMessageHandler implements Facade {
    private SimpMessagingTemplate template;
    protected SimpMessagingTemplate getTemplate() {
        return template;
    }

    private MessageAccountDataPopulator messageAccountDataPopulator;

    public MessageAccountDataPopulator getMessageAccountDataPopulator() {
        return messageAccountDataPopulator;
    }

    @Autowired
    protected void setMessageAccountDataPopulator(final MessageAccountDataPopulator messageAccountDataPopulator) {
        this.messageAccountDataPopulator = messageAccountDataPopulator;
    }

    @Autowired
    protected void setTemplate(final SimpMessagingTemplate template) {
        this.template = template;
    }

    public abstract void handle(final ReceiveMessageData message, final Principal principal);

    protected MessageAccountData generateAccountData(final AccountItem account) {
        return getMessageAccountDataPopulator().populate(account);
    }
}
