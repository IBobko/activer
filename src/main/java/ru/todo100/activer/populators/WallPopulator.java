package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.WallItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@SuppressWarnings("WeakerAccess")
public class WallPopulator implements Populator<WallItem, MessageData> {
    private MessageAccountDataPopulator messageAccountDataPopulator;
    @Autowired
    private AccountDao accountService;

    public MessageAccountDataPopulator getMessageAccountDataPopulator() {
        return messageAccountDataPopulator;
    }

    @Autowired
    protected void setMessageAccountDataPopulator(final MessageAccountDataPopulator messageAccountDataPopulator) {
        this.messageAccountDataPopulator = messageAccountDataPopulator;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @Override
    public MessageData populate(final WallItem wallItem) {
        final MessageData data = new MessageData();
        data.setMessage(wallItem.getText());
        data.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(wallItem.getAddedDate().getTime().getTime()));
        final AccountItem senderItem = getAccountService().get(wallItem.getSender());
        final MessageAccountData sender = getMessageAccountDataPopulator().populate(senderItem);
        data.setFrom(sender);
        return data;
    }

}
