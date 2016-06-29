package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.data.AccountGiftData;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.model.AccountGiftItem;
import ru.todo100.activer.model.GiftItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AccountGiftDataPopulator implements Populator<AccountGiftItem, AccountGiftData> {

    private MessageAccountDataPopulator messageAccountDataPopulator;

    public MessageAccountDataPopulator getMessageAccountDataPopulator() {
        return messageAccountDataPopulator;
    }

    @Autowired
    public void setMessageAccountDataPopulator(MessageAccountDataPopulator messageAccountDataPopulator) {
        this.messageAccountDataPopulator = messageAccountDataPopulator;
    }

    @Override
    public AccountGiftData populate(final AccountGiftItem accountGiftItem) {
        AccountGiftData accountGiftData = new AccountGiftData();
        final MessageAccountData sender = getMessageAccountDataPopulator().populate(accountGiftItem.getFrom());
        accountGiftData.setFrom(sender);
        GiftItem giftItem = accountGiftItem.getGift();
        accountGiftData.setFileName(giftItem.getFile());
        accountGiftData.setMessage(accountGiftItem.getMessage());
        accountGiftData.setGivenDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(accountGiftItem.getGivenDate().getTime()));
        return accountGiftData;
    }
}
