package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.FlirtMessageDao;
import ru.todo100.activer.dao.HappenedFlirtDao;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.FlirtMessageItem;
import ru.todo100.activer.model.HappenedFlirtItem;

import java.security.Principal;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class FlirtHandler extends AbstractMessageHandler {
    private HappenedFlirtDao happenedFlirtDao;
    private AccountDao accountService;
    private FlirtMessageDao flirtMessageDao;

    public HappenedFlirtDao getHappenedFlirtDao() {
        return happenedFlirtDao;
    }

    @Autowired
    public void setHappenedFlirtDao(HappenedFlirtDao happenedFlirtDao) {
        this.happenedFlirtDao = happenedFlirtDao;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public FlirtMessageDao getFlirtMessageDao() {
        return flirtMessageDao;
    }

    @Autowired
    public void setFlirtMessageDao(FlirtMessageDao flirtMessageDao) {
        this.flirtMessageDao = flirtMessageDao;
    }

    @Override
    public void handle(ReceiveMessageData message, Principal principal) {
        final HappenedFlirtItem happenedFlirtItem = (HappenedFlirtItem) getHappenedFlirtDao().get(message.getTo());

        final AccountItem account = getAccountService().get(principal.getName());
        final AccountItem opponent;
        if (happenedFlirtItem.getAccountAppliedId().equals(account.getId())) {
            opponent = getAccountService().get(happenedFlirtItem.getAccountInitId());
        } else {
            opponent = getAccountService().get(happenedFlirtItem.getAccountAppliedId());
        }

        final PacketMessageData messageData = new PacketMessageData();
        messageData.setFrom(generateAccountData(account));
        messageData.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(new GregorianCalendar().getTime()));
        messageData.setType(PopupMessageType.FLIRT_MESSAGE);
        messageData.setMessage(message.getMessage());

        final FlirtMessageItem flirtMessageItem = new FlirtMessageItem();
        flirtMessageItem.setAccountId(account.getId());
        flirtMessageItem.setFlirtId(happenedFlirtItem.getId());
        flirtMessageItem.setMessage(message.getMessage());
        flirtMessageItem.setSentDate(new GregorianCalendar());

        getFlirtMessageDao().save(flirtMessageItem);

        getTemplate().convertAndSendToUser(opponent.getUsername(), "/global2", messageData);
        getTemplate().convertAndSendToUser(principal.getName(), "/global2", messageData);

    }
}
