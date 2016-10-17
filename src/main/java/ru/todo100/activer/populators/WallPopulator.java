package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.WallAttachmentItem;
import ru.todo100.activer.model.WallItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@SuppressWarnings("WeakerAccess")
public class WallPopulator implements Populator<WallItem, MessageData> {
    @Value(value = "${static.host.files}")
    String staticFiles;
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
        data.setId(wallItem.getId());
        data.setMessage(wallItem.getText());
        data.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(wallItem.getAddedDate().getTime()));
        final AccountItem senderItem = getAccountService().get(wallItem.getSender());
        final MessageAccountData sender = getMessageAccountDataPopulator().populate(senderItem);
        data.setFrom(sender);
        if (wallItem.getAttachments() != null && !wallItem.getAttachments().isEmpty()) {
            WallAttachmentItem wallAttachmentItem = wallItem.getAttachments().iterator().next();
            data.setAttachmentFile(wallAttachmentItem.getPhoto());
            data.setAttachmentHtml("<img style='max-width:300px;max-height:300px' src='" + staticFiles + "/" + wallAttachmentItem.getPhoto() + ".'/>");
        }
        return data;
    }

}
